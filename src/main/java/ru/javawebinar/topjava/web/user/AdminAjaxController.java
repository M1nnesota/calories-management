package ru.javawebinar.topjava.web.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/ajax/admin/users")
public class AdminAjaxController extends AbstractUserController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createOrUpdate(@RequestParam("id") int id,
                               @RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam(required = false, value = "enabled", defaultValue = "true") boolean enabled,
                               @RequestParam(required = false, value = "roles", defaultValue = "ROLE_USER") String[] roleString) {

        User user = new User(id, name, email, password, Role.ROLE_USER);
        user.setEnabled(enabled);
        Set<Role> roles = new HashSet<>();
        for (int i = 0; i < roleString.length; i++) {
            roleString[i] = roleString[i].replaceAll("\\[", "").replaceAll("\\]","");
            roles.add(Role.valueOf(roleString[i]));
        }
        user.setRoles(roles);
        if (id == 0) {
            super.create(user);
        } else {
            super.update(user, id);
        }
    }
}
