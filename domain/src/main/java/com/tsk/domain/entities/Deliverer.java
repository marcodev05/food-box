package com.tsk.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("DEL")
@Data
public class Deliverer extends UserEntity{
    private boolean available;
}
