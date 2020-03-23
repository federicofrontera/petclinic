package fede.springframework.petclinic.controllers;

import fede.springframework.petclinic.model.Owner;
import fede.springframework.petclinic.model.Pet;
import fede.springframework.petclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"/find"})
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping()
    public String processFindForm(Owner owner, BindingResult result, Model model){
        //parameterless GET returns  all records
        if (owner.getLastName() == null ){
            owner.setLastName(""); //empty string to find all
        }
        //find owners by last name
        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
        if (results.isEmpty()){
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if(results.size() == 1){
            //1 result
            owner = results.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else {
            //multiple results
            model.addAttribute("owners", results);
            return "owners/ownerList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        Owner owner = this.ownerService.findById(ownerId);
/*      for (Pet pet : owner.getPets()) {
            pet.setVisitsInternal(visits.findByPetId(pet.getId()));
        }*/
        mav.addObject(owner);
        return mav;
    }
}
