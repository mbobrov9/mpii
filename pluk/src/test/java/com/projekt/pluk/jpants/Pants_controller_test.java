package com.projekt.pluk.jpants;
import com.projekt.pluk.models.Role;
import com.projekt.pluk.repos.UserRepo;
import com.projekt.pluk.controllers.pants_controller;
import com.projekt.pluk.repos.pants_req_repo;
import com.projekt.pluk.models.User;
import com.projekt.pluk.models.pants_req;
import com.projekt.pluk.models.User;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Pants_controller_test
{
    public Model model=new Model() {
        @Override
        public Model addAttribute(String attributeName, Object attributeValue) {
            return null;
        }

        @Override
        public Model addAttribute(Object attributeValue) {
            return null;
        }

        @Override
        public Model addAllAttributes(Collection<?> attributeValues) {
            return null;
        }

        @Override
        public Model addAllAttributes(Map<String, ?> attributes) {
            return null;
        }

        @Override
        public Model mergeAttributes(Map<String, ?> attributes) {
            return null;
        }

        @Override
        public boolean containsAttribute(String attributeName) {
            return false;
        }

        @Override
        public Object getAttribute(String attributeName) {
            return null;
        }

        @Override
        public Map<String, Object> asMap() {
            return null;
        }
    };
    @Autowired
    private pants_controller pants_contrl;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private pants_req_repo pants_repo;

    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "PLUK" })
    public void my_pants( )
{
    User user2=new User();
    long id = 42;
    user2.setId(id);
    user2.setRoles(Collections.singleton(Role.PLUK));

    String str=pants_contrl.my_pants(user2, model);
    Mockito.verify(pants_repo, Mockito.times(1)).findByAuthor(user2);
    Assert.assertEquals("my_pants", str);
}
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "PLUK" })
    public void post_new_pants ( )
    {
        User user=new User();
        long id = 42;
        String number="123";
        String fulltext="i need it";
        String type= "Жёлтые";
        pants_req pan;
        String str=pants_contrl.post_new_pants(user, number,   fulltext,  type,  model);
        //Mockito.when(pants_repo.save(ArgumentMatchers.any(pants_req.class)))
          //      .thenReturn(pants_req);
        ArgumentCaptor<pants_req> requestCaptor = ArgumentCaptor.forClass(pants_req.class);
        Mockito.verify(pants_repo, Mockito.times(1)).save(ArgumentMatchers.any(pants_req.class));
        Mockito.verify(pants_repo, Mockito.times(1)).save(requestCaptor.capture());
        pan=requestCaptor.getValue();
        Assert.assertEquals(number, pan.getCount());
        Assert.assertEquals(fulltext, pan.getFull_text());
        Assert.assertEquals(user, pan.getAuthor());
        Assert.assertEquals(type, pan.getType());
        Assert.assertEquals("/new_pants", str);
    }

    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "PLUK" })
    public void new_pants( )
    {
        String str=pants_contrl.new_pants(model);
        Assert.assertEquals("new_pants", str);
    }

    @Test
    @WithMockUser(username = "admin", authorities = { "SHT" })
    public void pants_por_pg( )
    {

        ArgumentCaptor<pants_req> requestCaptor = ArgumentCaptor.forClass(pants_req.class);
        String str=pants_contrl.pants_por_pg( model);
        Mockito.verify(pants_repo, Mockito.times(3)).findByDecision(Mockito.any());
        Mockito.verify(pants_repo, Mockito.times(1)).findByDecision("новое");
        Mockito.verify(pants_repo, Mockito.times(1)).findByDecision("Одобрено");
        Mockito.verify(pants_repo, Mockito.times(1)).findByDecision("Пошит");
        //Mockito.verify(model, Mockito.times(3)).addAttribute(ArgumentMatchers.any());
        Assert.assertEquals("/pants_for_pg", str);
    }
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "PLUK" })
    public void pants_por_fab( )
    {
        //ArgumentCaptor<Iterable<pants_req>> requestCaptor;
        String str=pants_contrl.pants_por_fab( model);
        Mockito.verify(pants_repo, Mockito.times(1)).findByDecision("На производстве");

        Assert.assertEquals("/pants_for_fab", str);
    }
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "PG" })
    public void pants( )
    {
        String str=pants_contrl.pants( model);
        Mockito.verify(pants_repo, Mockito.times(1)).findByDecision("На одобрении");
        Assert.assertEquals("/pants", str);
    }
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "PG" })
    public void delete_pants( )
    {
        ArgumentCaptor<pants_req> requestCaptor = ArgumentCaptor.forClass(pants_req.class);
        pants_req pan=new pants_req();
        long id = 42;
        pan.setId(id);
        pants_repo.save(pan);
        String str=pants_contrl.delete_law( pan.getId(),model);
        Mockito.verify(pants_repo, Mockito.times(1)).findById(ArgumentMatchers.eq(id));
        Mockito.verify(pants_repo, Mockito.times(1)).delete(ArgumentMatchers.any());

        Assert.assertEquals("redirect:/pants", str);
    }
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "PG" })
    public void edit_pants( )
    {
        ArgumentCaptor<pants_req> requestCaptor = ArgumentCaptor.forClass(pants_req.class);
        pants_req pan=new pants_req();
        long id=165;

        String str=pants_contrl.edit_pants( id,model);
        Mockito.verify(pants_repo, Mockito.times(1)).findById(ArgumentMatchers.eq(id));
        Mockito.verify(pants_repo, Mockito.times(1)).save(ArgumentMatchers.any(pants_req.class));
        Mockito.verify(pants_repo, Mockito.times(1)).save(requestCaptor.capture());
        pan=requestCaptor.getValue();
        Assert.assertEquals("Одобрено", pan.getDecision());
        Assert.assertEquals("redirect:/pants", str);
    }
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "SHT" })
    public void edit_pants_for_pg( )
    {
        ArgumentCaptor<pants_req> requestCaptor = ArgumentCaptor.forClass(pants_req.class);
        pants_req pan=new pants_req();
        long id=165;

        String str=pants_contrl.edit_pants_for_pg( id,model);
        Mockito.verify(pants_repo, Mockito.times(1)).findById(ArgumentMatchers.eq(id));
        Mockito.verify(pants_repo, Mockito.times(1)).save(ArgumentMatchers.any(pants_req.class));
        Mockito.verify(pants_repo, Mockito.times(1)).save(requestCaptor.capture());
        pan=requestCaptor.getValue();
        Assert.assertEquals("На одобрении", pan.getDecision());
        Assert.assertEquals("redirect:/pants_for_pg", str);
    }

    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "SHT" })
    public void edit_pants_for_pgg( )
    {
        ArgumentCaptor<pants_req> requestCaptor = ArgumentCaptor.forClass(pants_req.class);
        pants_req pan=new pants_req();
        long id=165;

        String str=pants_contrl.edit_pants_for_pgg( id,model);
        Mockito.verify(pants_repo, Mockito.times(1)).findById(ArgumentMatchers.eq(id));
        Mockito.verify(pants_repo, Mockito.times(1)).save(ArgumentMatchers.any(pants_req.class));
        Mockito.verify(pants_repo, Mockito.times(1)).save(requestCaptor.capture());
        pan=requestCaptor.getValue();
        Assert.assertEquals("На производстве", pan.getDecision());
        Assert.assertEquals("redirect:/pants_for_pg", str);
    }
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "SHT" })
    public void edit_pants_for_pggg( )
    {
        ArgumentCaptor<pants_req> requestCaptor = ArgumentCaptor.forClass(pants_req.class);
        pants_req pan=new pants_req();
        long id=165;

        String str=pants_contrl.edit_pants_for_pggg( id,model);
        Mockito.verify(pants_repo, Mockito.times(1)).findById(ArgumentMatchers.eq(id));
        Mockito.verify(pants_repo, Mockito.times(1)).save(ArgumentMatchers.any(pants_req.class));
        Mockito.verify(pants_repo, Mockito.times(1)).save(requestCaptor.capture());
        pan=requestCaptor.getValue();
        Assert.assertEquals("Готовы к выдаче", pan.getDecision());
        Assert.assertEquals("redirect:/pants_for_pg", str);
    }
    @Test
    @WithMockUser(username = "admin", authorities = { "ADMIN", "FAB" })
    public void edit_pants_for_fab( )
    {
        ArgumentCaptor<pants_req> requestCaptor = ArgumentCaptor.forClass(pants_req.class);
        pants_req pan=new pants_req();
        long id=165;

        String str=pants_contrl.edit_pants_for_fab( id,model);
        Mockito.verify(pants_repo, Mockito.times(1)).findById(ArgumentMatchers.eq(id));
        Mockito.verify(pants_repo, Mockito.times(1)).save(ArgumentMatchers.any(pants_req.class));
        Mockito.verify(pants_repo, Mockito.times(1)).save(requestCaptor.capture());
        pan=requestCaptor.getValue();
        Assert.assertEquals("Пошит", pan.getDecision());
        Assert.assertEquals("redirect:/pants_for_fab", str);
    }
}