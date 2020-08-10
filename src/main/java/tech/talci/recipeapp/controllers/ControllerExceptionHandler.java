package tech.talci.recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import tech.talci.recipeapp.exceptions.NotFoundException;

//Set up global coverage of exception handling
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormatException(Model model, Exception exception){

        log.error("Handling number exception error");
        log.error(exception.getMessage());
        model.addAttribute("exception", exception);

        return "errorPages/400error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(Model model, Exception exception){

        log.error("Handling not found exception");
        model.addAttribute("exception", exception);

        return "errorPages/404error";
    }
}
