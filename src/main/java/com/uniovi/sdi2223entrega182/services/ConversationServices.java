package com.uniovi.sdi2223entrega182.services;

import com.uniovi.sdi2223entrega182.entities.Conversation;
import com.uniovi.sdi2223entrega182.entities.Message;
import com.uniovi.sdi2223entrega182.entities.Offer;
import com.uniovi.sdi2223entrega182.entities.User;
import com.uniovi.sdi2223entrega182.repositories.ConversationRepository;
import com.uniovi.sdi2223entrega182.repositories.MessageRepository;
import com.uniovi.sdi2223entrega182.repositories.OffersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConversationServices {

    @Autowired
    private ConversationRepository conversationsRepository;
    @Autowired
    private OffersRepository offersRepository;
    @Autowired
    private MessageRepository messageRepository;
    private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    /**
     * Devuelve la conversación del usuario actual
     * @param offer La oferta
     * @param actualUser El usuario
     * @return La conversación
     */
    public Conversation getConversation(Offer offer, User actualUser) {
        Conversation c = conversationsRepository.findByOfferAndUsers( actualUser,offer);
        if (c == null) {
            c = conversationsRepository.save(new Conversation(offer, actualUser, offer.getUser()));
              if(c.getCreatorConver().getEmail().equals(c.getCreatorOffer().getEmail())){
                  conversationsRepository.deleteById(c.getId());
                  c = null;
              }
              if(!c.getCreatorConver().getEmail().equals(actualUser.getEmail())&&!c.getCreatorOffer().getEmail().equals(actualUser.getEmail())){
                  conversationsRepository.deleteById(c.getId());
                  return null;
              }
            logger.info(String.format("Conversation created"));

        }


        return c;
    }

    /**
     * Devuelve los mensajes
     * @param c La conversación
     * @return Los mensajes
     */
    public List<Message> getMessages(Conversation c) {
        return messageRepository.findMessages(c);
    }

    /**
     * Devuelve la conversación concreta
     * @param idConversation La conversación
     * @return La conversación
     */
    public Conversation getConversation(Long idConversation) {

       Conversation c =  conversationsRepository.findById(idConversation).get();
        if(c.getCreatorConver().getEmail().equals(c.getCreatorOffer().getEmail())){
            conversationsRepository.deleteById(c.getId());
            return null;
        }

        return c;

    }

    /**
     * Devuelve la conversación
     * @param idConversation id
     * @param u usuario
     * @return Conversación
     */
    public Conversation getConversation(Long idConversation,User u) {

        Conversation c =  conversationsRepository.findById(idConversation).get();
        if(c.getCreatorConver().getEmail().equals(c.getCreatorOffer().getEmail())){
            conversationsRepository.deleteById(c.getId());
            return null;
        }
        if(!c.getCreatorConver().getEmail().equals(u.getEmail())&&!c.getCreatorOffer().getEmail().equals(u.getEmail())){
            return null;
        }
        logger.info(String.format("Conversation opened"));
        return c;

    }

    /**
     * Devuelve la lista de conversaciones
     * @param u Usuario
     * @return Lista
     */
    public List<Conversation> getConversations(User u) {
        return conversationsRepository.findByUser(u);
    }

    /**
     * Envía un mensaje
     * @param c Conversación
     * @param actualUser Usuario
     * @param to Receptor
     * @param text Mensaje
     */
    @Transactional
    public void sendMessage(Conversation c, User actualUser, User to, String text) {
        messageRepository.save(new Message(new Date(), text, c, actualUser, to));
        logger.info(String.format("message sended"));
    }

    /**
     * Borra una conversación
     * @param id Id
     * @param idUser Usuario
     * @return Conversación
     */
    public Conversation deleteConversation(Long id, Long idUser) {
        Conversation c = conversationsRepository.findByUserAndId(id, idUser);
        if (c != null) {
           conversationsRepository.deleteById(id);
            logger.info(String.format("Conversation %o has been deleted ", id));
          return c;
        }

        return null;
    }
}