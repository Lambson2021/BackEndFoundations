package com.dogo.chat;

import org.springframework.data.jpa.repository.JpaRepository;

//points the message repositry interface to jpa repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
