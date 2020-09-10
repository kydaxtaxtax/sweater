package org.example.sweater.repos;

import org.example.sweater.domian.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag); // поиск по тегу tag
}
