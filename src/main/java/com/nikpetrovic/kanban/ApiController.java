/**
 * 
 */
package com.nikpetrovic.kanban;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nikpetrovic.kanban.model.Card;
import com.nikpetrovic.kanban.model.Task;

/**
 * @author Nikola.Petrovic
 * @created Apr 20, 2016
 *
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    private List<Card> cards = new ArrayList<Card>() {
        {
            add(new Card(1, "Implement React Components", "Finish implementation of React Kanban App", "#c9c9c9",
                    "in-progress", new ArrayList<Task>() {
                        {
                            add(new Task(1, "Implement Kanban Board", true));
                            add(new Task(2, "Implement Kanban Components", true));
                            add(new Task(3, "Implement Kanban Router", true));
                            add(new Task(4, "Implement Kanban App with Flux", false));
                        }
                    }));
            add(new Card(2, "Read The Book", "I should read the **whole** book", "#bd8d31", "in-progress",
                    new ArrayList<Task>() {
                        {
                            add(new Task(1, "Chapter 1", true));
                            add(new Task(2, "Chapter 2", true));
                            add(new Task(3, "Chapter 3", false));
                            add(new Task(4, "Chapter 4", false));
                        }
                    }));

            add(new Card(3, "Watch The Basketball", "Basketball Game on Sunday", "#4dab2c", "todo",
                    new ArrayList<Task>() {
                        {
                            add(new Task(1, "Buy Nachos", false));
                            add(new Task(2, "Invite Friends", false));
                        }
                    }));
            add(new Card(4, "Clean The House", "Do a weekly home cleaning", "#0080ff", "todo", new ArrayList<Task>() {
                {
                    add(new Task(1, "Take the Garbage Out", false));
                    add(new Task(2, "Wash the Clothes", false));
                    add(new Task(3, "Vacuuming", false));
                }
            }));
            add(new Card(5, "Learn AngularJS", "Create Sample App With AngularJS", "#121232", "done",
                    new ArrayList<Task>() {
                        {
                            add(new Task(1, "Learn JavaScript", true));
                            add(new Task(2, "Learn jQuery", true));
                            add(new Task(3, "Learn Angular", true));
                        }
                    }));
            add(new Card(6, "Repair Tha Car", "Repair The Car after Accident", "#3dfa23", "done",
                    new ArrayList<Task>() {
                        {
                            add(new Task(1, "Take the Car to Repairer", false));
                            add(new Task(2, "Pay", false));
                            add(new Task(3, "Take the Car from Repairer", false));
                            add(new Task(4, "Take the Car to Repairer again", false));
                        }
                    }));
        }
    };

    @RequestMapping(value = "/cards", method = RequestMethod.POST)
    public Card addCard(@RequestBody Card card) {
        Optional<Card> max = cards.stream().max((c1, c2) -> Long.compare(c1.getId(), c2.getId()));
        long newId = 1;
        if (max.isPresent()) {
            newId = max.get().getId() + 1;
        }
        card.setId(newId);
        cards.add(card);
        return card;
    }

    @RequestMapping(value = "/cards/{cardId}/tasks", method = RequestMethod.POST)
    public Task addTask(@PathVariable long cardId, @RequestBody Task task) {
        Optional<Card> cardFound = cards.stream().filter(c -> c.getId() == cardId).findFirst();
        if (cardFound.isPresent()) {
            Card card = cardFound.get();
            if (card.getTasks() == null) {
                card.setTasks(new ArrayList<Task>());
            }

            Optional<Task> maxCard = card.getTasks().stream().max((t1, t2) -> Long.compare(t1.getId(), t2.getId()));
            long newId = 1;
            if (maxCard.isPresent()) {
                newId = maxCard.get().getId() + 1;
            }
            task.setId(newId);
            card.getTasks().add(task);
        } else {
            throw new RuntimeException(String.format("Card with id %d is not found.", cardId));
        }

        return task;
    }

    @RequestMapping(value = "/cards/{cardId}/tasks/{taskId}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable long cardId, @PathVariable long taskId) {
        Optional<Card> cardFound = cards.stream().filter(c -> c.getId() == cardId).findFirst();
        if (cardFound.isPresent()) {
            Card card = cardFound.get();
            if (card.getTasks() != null) {
                card.getTasks().removeIf(t -> t.getId() == taskId);
            }
        }
    }

    @RequestMapping("/cards")
    public List<Card> getCards() {
        return cards;
    }
    
    @RequestMapping(value = "/cards/{cardId}", method = RequestMethod.PUT)
    public void updateCard(@PathVariable long cardId, @RequestBody Card card) {
        Optional<Card> cardFound = cards.stream().filter(c -> c.getId() == cardId).findFirst();
        if (cardFound.isPresent()) {
            Card c = cardFound.get();
            c.setStatus(card.getStatus());
            c.setColor(c.getColor());
            c.setDescription(c.getDescription());
            c.setTitle(card.getTitle());
        } else {
            throw new RuntimeException(String.format("Card with id %d is not found.", cardId));
        }
    }

    @RequestMapping(value = "/cards/{cardId}/tasks/{taskId}/{value}")
    public void updateTaskState(@PathVariable int cardId, @PathVariable int taskId, @PathVariable boolean value) {
        Optional<Card> cardFound = cards.stream().filter(card -> card.getId() == cardId).findFirst();
        if (cardFound.isPresent()) {
            Card c = cardFound.get();
            if (c.getTasks() != null) {
                Optional<Task> taskFound = c.getTasks().stream().filter(t -> t.getId() == taskId).findFirst();
                if (taskFound.isPresent()) {
                    taskFound.get().setDone(value);
                } else {
                    throw new RuntimeException(String.format("Task with id %d is not found.", taskId));
                }
            }
        } else {
            throw new RuntimeException(String.format("Card with id %d is not found.", cardId));
        }
    }
}
