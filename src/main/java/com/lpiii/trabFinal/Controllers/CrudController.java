package com.lpiii.trabFinal.Controllers;

import com.lpiii.trabFinal.Utils.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.StringUtils.capitalize;

@Controller
public class CrudController {

    private static final String ENTITY_PACKAGE = "com.lpiii.trabFinal.Entities";

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/{entity}")
    public String handleEntity(@PathVariable String entity, Model model) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Class<?> entityClass = getClassForEntity(entity);
        Object repository = getRepositoryForEntity(entity);

        List<String> columns = getColumnsForEntity(entityClass);
        List<Map<String, Object>> rows = getRowsForEntity(repository);

        model.addAttribute("entityName", entity);
        model.addAttribute("tableData", new TableData(columns, rows));

        return "pages/crud";
    }

    private Class<?> getClassForEntity(String entity) throws ClassNotFoundException {
        String className = ENTITY_PACKAGE + "." + capitalize(entity);
        return Class.forName(className);
    }

    private Object getRepositoryForEntity(String entity) {
        String beanName = entity.toLowerCase() + "Repository";
        return applicationContext.getBean(beanName);
    }

    private List<String> getColumnsForEntity(Class<?> entityClass) {
        List<String> columns = new ArrayList<>();
        for (Field field : entityClass.getDeclaredFields()) {
            columns.add(capitalize(field.getName()));
        }
        return columns;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getRowsForEntity(Object repository) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<?> entities = (List<?>) repository.getClass().getMethod("findAll").invoke(repository);

        List<Map<String, Object>> rows = new ArrayList<>();
        for (Object entity : entities) {
            Map<String, Object> row = new HashMap<>();
            for (Field field : entity.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    row.put(field.getName(), field.get(entity));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Erro ao acessar campo: " + field.getName(), e);
                }
            }
            rows.add(row);
        }

        return rows;
    }
}
