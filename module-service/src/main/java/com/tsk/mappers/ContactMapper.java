package com.tsk.mappers;

import com.tsk.domain.dto.auth.UserRequest;
import com.tsk.domain.dto.contact.ContactDto;
import com.tsk.domain.entities.Contact;
import org.mapstruct.Mapper;

@Mapper
public interface ContactMapper {

    Contact fromRequestToContact(UserRequest request);

    Contact fromContactDtoToContact(ContactDto contactDto);
}
