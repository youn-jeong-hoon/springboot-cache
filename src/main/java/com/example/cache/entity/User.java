package com.example.cache.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "t_user")
public class User {
  @Id
  private String userId;
  private String name;
  private int age;
}
