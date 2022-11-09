package com.tsk.serviceImpl.delivery;

import antlr.Utils;
import com.tsk.dao.ContactRepository;
import com.tsk.dao.RoleRepository;
import com.tsk.dao.UserRepository;
import com.tsk.domain.dto.auth.AuthResponse;
import com.tsk.domain.dto.auth.UserRequest;
import com.tsk.domain.entities.Contact;
import com.tsk.domain.entities.Deliverer;
import com.tsk.domain.entities.UserEntity;
import com.tsk.exception.ResourceNotFoundException;
import com.tsk.mappers.ContactMapper;
import com.tsk.mappers.UserMapper;
import com.tsk.services.auth.IAuthService;
import com.tsk.services.delivery.IDeliveryService;
import com.tsk.services.file.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements IDeliveryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IAuthService iAuthService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ContactMapper contactMapper;

    @Autowired
    UserMapper mapper;
    @Override
    public List<Deliverer> fetchAllDeliverer() {
        List<Deliverer> deliverers = userRepository.findAllDeliverer();
        return  deliverers;
    }

    @Override
    public List<Deliverer> fetchAllAvailableDeliverer() {
        return userRepository.findByAvailableTrue();
    }

    @Override
    public AuthResponse addDeliverer(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException("Email already used");
        } else {
            try {
                Deliverer deliverer = mapper.ToDeliverer(userRequest);

                Contact contact;
                contact = contactMapper.fromRequestToContact(userRequest);
                if(userRequest.getFile() != null){
                    String uriFile = this.getUriFile(userRequest.getFile());
                    contact.setPicture(uriFile);
                }
                contact = contactRepository.save(contact);
                deliverer.setContact(contact);
                deliverer.setAvailable(true);
                deliverer.setRoles(List.of(roleRepository.findByName("DELIVERER")));
                UserEntity createdUser = userRepository.save(deliverer);

                AuthResponse response = new AuthResponse();
                response.setId(createdUser.getUserId());
                response.setFirstname(contact.getFirstname());
                response.setLastname(contact.getLastname());
                response.setEmail(createdUser.getEmail());
                response.setPicture(contact.getPicture());

                List<String> roles = createdUser.getRoles().stream()
                        .map(r -> r.getName())
                        .collect(Collectors.toList());
                response.setRoles(roles);
                return response;
            } catch (Exception ex) {
                return null;
            }
        }
    }

    private String getUriFile(MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return fileUri;
    }
}


