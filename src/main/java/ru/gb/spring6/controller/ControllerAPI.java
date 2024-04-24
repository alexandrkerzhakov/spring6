package ru.gb.spring6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gb.spring6.domain.Characters;
import ru.gb.spring6.domain.Result;
import ru.gb.spring6.service.ServiceApi;

@Controller
public class ControllerAPI {
    @Autowired
    private ServiceApi serviceApi;

    /**
     * GET запрос на localhost:8080/
     * @return "main" (страница со всеми пользователями, имеющими ссылки на @GetMapping("/{id}"))
     */
    @GetMapping("/")
    public String getCharacters(Model model) {
        Characters allCharacters = serviceApi.getAllCharacters();
        model.addAttribute("allCharacters", allCharacters);
        return "main";
    }

    /**
     * GET запрос на localhost:8080/id
     * @return "description" (страница c каждым персонажем с возможностью навигации по их списку)
     */
    @GetMapping("/{id}")
    public String getOneCharacter(@PathVariable("id") int id, Model model) {
        Characters allCharacters = serviceApi.getAllCharacters();
        Result findResultOnName = allCharacters.getResults()
                .stream()
                .filter(result -> result.getId() == id)
                .findAny()
                .orElseThrow();
        model.addAttribute("character", findResultOnName);
        model.addAttribute("countCharacter", allCharacters.getResults().size());
        return "description";
    }
}
