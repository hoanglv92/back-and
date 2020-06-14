package hoanglinh.eshop.controllers.product;

import hoanglinh.eshop.models.product.Phone;
import hoanglinh.eshop.models.product.PhoneForm;
import hoanglinh.eshop.models.user.User;
import hoanglinh.eshop.services.product.PhoneService;
import hoanglinh.eshop.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@CrossOrigin("*")
public class Product {
    @Autowired
    Environment env;
    @Autowired
    private UserService userService;
    @Autowired
    private PhoneService phoneService;

    @GetMapping("/api/getListProduct")
    public ResponseEntity<Page<Phone>> listProduct(@PageableDefault(size = 3) Pageable pageable){
        try {
            Page<Phone> phoneList = phoneService.findAllByPage(pageable);
            return new ResponseEntity<>(phoneList, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/api/getListProductHot")
    public ResponseEntity<Page<Phone>> listProductHot(@PageableDefault(size = 3) Pageable pageable){
        try {
            Page<Phone> listPhoneHot =phoneService.findAllOrderByPurchaseDesc(pageable);
            return new ResponseEntity<>(listPhoneHot, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping(value = "/api/createProduct", consumes = "multipart/form-data")
    public ResponseEntity<Void> createProduct(@RequestBody PhoneForm phoneForm,
                                              @RequestPart(value = "image") MultipartFile imageProduct){

        String imageUpload =env.getProperty("imgProduct").toString();
        String imageName= imageProduct.getOriginalFilename();
        User user = userService.findByUserName(phoneForm.getUser().getUserName());
        try {
            FileCopyUtils.copy(imageProduct.getBytes(), new File(imageUpload + imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Phone phone= new Phone(phoneForm.getName(),imageName,phoneForm.getProducer(),
                phoneForm.getDescription(), (java.sql.Date) phoneForm.getProductionDate(),phoneForm.getSale(),phoneForm.getQuantity(),phoneForm.getPrice(),user);

        try {
            phoneService.save(phone);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/editProduct")
    public ResponseEntity<Void> editProduct(@RequestBody PhoneForm phoneForm,
                                            @RequestPart(value = "image") MultipartFile imageProduct){
        String imageUpload =env.getProperty("imgProduct");
        String imageName=imageProduct.getOriginalFilename();
        User user = userService.findByUserName(phoneForm.getUser().getUserName());

        try {
            FileCopyUtils.copy(imageProduct.getBytes(),new File(imageUpload + imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Phone phone= new Phone(phoneForm.getName(),imageName,phoneForm.getProducer(),
                phoneForm.getDescription(), (java.sql.Date) phoneForm.getProductionDate(),phoneForm.getSale(),phoneForm.getQuantity(),phoneForm.getPrice(),user);

        try {
            phoneService.save(phone);
            return  new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/deleteProduct/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable Long id){
        Phone phone= phoneService.findOne(id);
       try {
           phoneService.delete(phone.getId());
           return new ResponseEntity<>(HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }


}
