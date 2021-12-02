package com.yang.controller.category;

import com.yang.domain.BlogVO;
import com.yang.domain.CategoryVO;
import com.yang.domain.UserVO;
import com.yang.service.BlogService;
import com.yang.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryApiController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    BlogService blogService;

    @GetMapping("/blogAdminCategory/{blogId}")
    public String blogAdminCategoryGet(@PathVariable int blogId,
                                       Model model) {
        BlogVO blog = blogService.getBlog(blogId);
        List<CategoryVO> categoryList = categoryService.getCategoryList(blog);
        model.addAttribute("blog", blog);
        model.addAttribute("categoryList", categoryList);
        return "forward:/blogAdminCategoryView";
    }


    @PostMapping("/blogAdminCategory/{blogId}")
    public String blogAdminCategoryPost(@PathVariable int blogId,
                                        @ModelAttribute CategoryVO category) {
        category.setBlogId(blogId);
        categoryService.insertCategory(category);
        return "redirect:/blogAdminCategory/"+blogId;
    }

    @GetMapping("/deleteCategory")
    public String blogAdminCategoryDelete(@RequestParam int blogId,
                                          @RequestParam int categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/blogAdminCategory/"+blogId;
    }

    @GetMapping("/getCategory/{categoryId}")
    public String getCategory(@PathVariable int categoryId, Model model) {
        CategoryVO category = categoryService.getCategory(categoryId);
        BlogVO blog = blogService.getBlog(category.getBlogId());
        List<CategoryVO> categoryList = categoryService.getCategoryList(blog);
        model.addAttribute("blog", blog);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("category", category);
        return "forward:/blogAdminCategoryUpdateView";
    }

    @PostMapping("/updateCategory/{categoryId}")
    public String updateCategory(@ModelAttribute CategoryVO category,
                                 @PathVariable int categoryId) {
        category.setCategoryId(categoryId);
        categoryService.updateCategory(category);
        int blogId = categoryService.getCategory(categoryId).getBlogId();
        return "redirect:/blogAdminCategory/"+blogId;
    }
}
