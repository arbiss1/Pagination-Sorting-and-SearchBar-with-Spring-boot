package arbis.demo.app.controller;

import arbis.demo.app.domain.Users;
import arbis.demo.app.repository.UsersPaginationRepo;
import arbis.demo.app.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@org.springframework.stereotype.Controller

public class Controller {

    @Autowired
    UsersPaginationRepo paginationRepo;

    @Autowired
    UsersRepository usersRepository;


    @GetMapping("/request")
    public ResponseEntity<Users> getUsers(@RequestParam(defaultValue = "0") int pageNo,
                                          @RequestParam(defaultValue = "5") int pageSize){
        PageRequest iteratePage = PageRequest.of(pageNo, pageSize, Sort.by("fullName").ascending());
        Page<Users> allUsers = paginationRepo.findAll(iteratePage);
        if(allUsers.hasContent()){
            return new ResponseEntity(allUsers.getContent(), HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity("Empty", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return pagination(1, "fullName", "asc", model);
    }

    @GetMapping("/home/{pageNo}")
    public String pagination(@PathVariable(value = "pageNo") int pageNo,
                             @RequestParam("sortField") String sortField,
                             @RequestParam("sortDir") String sortDir,
                       Model model){
        int pageSize = 5 ;

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        PageRequest iteratePage = PageRequest.of(pageNo - 1, pageSize, sort);

        Page<Users> allUsers = paginationRepo.findAll(iteratePage);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", allUsers.getTotalPages());
        model.addAttribute("totalItems", allUsers.getTotalElements());
        model.addAttribute("users",allUsers);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

            return "home";

    }


    @GetMapping("/requestFirstName")
    public ResponseEntity<Users> listByFirstName(@RequestParam String fullname,
                                                 @RequestParam(defaultValue = "1") int pageNo,
                                                 @RequestParam(defaultValue = "5") int pageSize
                                                 ){
        Pageable iteratePage =  PageRequest.of(pageNo - 1, pageSize);
        Slice<Users> listByName = paginationRepo.findByFullName(fullname,iteratePage);

        List<Users> list = listByName.getContent();

        return new ResponseEntity(list,HttpStatus.ACCEPTED);
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model  model){
        List<Users> searchUser = usersRepository.search(keyword);
        model.addAttribute("users",searchUser);
        return "home";
    }

    @GetMapping("/serachFirstName")
    public ResponseEntity<Users> searchByFirstName(@RequestParam String firstName){
        List<Users> searchUserByName = usersRepository.serachByFirstName(firstName);
        return new ResponseEntity(searchUserByName,HttpStatus.ACCEPTED);
    }
}
