package jpabook.jpashop.controller;


import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.dto.BookDto;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form",new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(@ModelAttribute("form") BookForm form){
        Book book = new Book();
        // 좋은 설계 : Book class 안에 있는 setter 를 그 클래스 내에서만 쓸 수 있도록 조치하고 (protected 한 기본 생성자를 생성함으로써 )
        // Book class 안에 createBook() 핵심 비즈니스 메서를 넣어서 생성될 때 값을 넣는 방식으로 해야함 (Order class의 createOrder 참고할것)
        // 

        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        //BookDto items = new ModelMapper().map(items0, BookDto.class);
        model.addAttribute("items",items);
        return "items/itemList";

    }

    @GetMapping("/items/{itemId}/edit")
    public String edit(@PathVariable("itemId")Long id, Model model){
        Book item = (Book)itemService.findOne(id);
        // BookEntity 가 아닌 BookForm을 보낸다
//        BookForm form = new BookForm();
//        form.setId(item.getId());
//        form.setName(item.getName());
//        form.setAuthor(item.getAuthor());
//        form.setIsbn(item.getIsbn());
//        form.setPrice(item.getPrice());
//        form.setStockQuantity(item.getStockQuantity());

        // 귀찮으니께 modelMapper 쓰쟈..
        BookForm form = new ModelMapper().map(item, BookForm.class);
        model.addAttribute("form",form);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@ModelAttribute("form") BookForm form,@PathVariable("itemId")Long itemId){
        //준영속 엔티티!
//        Book book = new ModelMapper().map(form, Book.class);
//        itemService.saveItem(book);
        itemService.updateItem(itemId,form.getName(),form.getPrice(), form.getStockQuantity());
        return "redirect:/items";
    }
}
