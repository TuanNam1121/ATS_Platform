package org.ats.exceptions;

import jakarta.persistence.NoResultException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AppExceptionHandling {

    @ExceptionHandler(NoResultException.class)
    public ModelAndView noResultObject(NoResultException ex){
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMessage", "Invalid email or password");
        mv.setViewName("auths/login");
        return mv;

    }
}
