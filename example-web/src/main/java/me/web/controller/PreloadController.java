package me.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by chn on 15/12/15.
 */
@Controller
@RequestMapping("/preload")
public class PreloadController {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    ApplicationContext applicationContext;

    @RequestMapping(method= RequestMethod.GET)
    public ModelAndView get(ModelMap model) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        ModelAndView modelAndView = new ModelAndView("tool/show");
        StringBuilder beans = new StringBuilder();
        for(String bean: applicationContext.getBeanDefinitionNames()) {
            beans.append(bean).append("<br>").append(applicationContext.getBean(bean)).append("<br><br>");
        }

        //beans.append(testAddHourlyEmployee()).append("<br><br>").append(testAddSalariedEmployee()).append("<br>");

        modelAndView.addObject("context", beans);
        log.info("preload successfully.");
        return modelAndView;
    }
}
