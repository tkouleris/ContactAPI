package com.tkouleris.contacts.dao;

import com.tkouleris.contacts.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Long> {
}
