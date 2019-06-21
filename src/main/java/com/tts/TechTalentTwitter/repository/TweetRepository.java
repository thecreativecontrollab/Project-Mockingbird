package com.tts.TechTalentTwitter.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.TechTalentTwitter.model.Tweet;
import com.tts.TechTalentTwitter.model.TweetDisplay;
import com.tts.TechTalentTwitter.model.User;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, Long> {
    List<TweetDisplay> findAllByOrderByCreatedAtDesc();
    List<TweetDisplay> findAllByUserOrderByCreatedAtDesc(User user);
    List<TweetDisplay> findAllByUserInOrderByCreatedAtDesc(List<User> users);
    List<TweetDisplay> findByTags_PhraseOrderByCreatedAtDesc(String phrase);

}