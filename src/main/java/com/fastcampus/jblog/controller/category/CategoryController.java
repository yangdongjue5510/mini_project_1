package com.fastcampus.jblog.controller.category;

import com.fastcampus.jblog.biz.blog.BlogService;
import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.category.CategoryService;
import com.fastcampus.jblog.biz.category.CategoryVO;
import com.fastcampus.jblog.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/blogAdminCategory")
    public String blogAdminCategory(BlogVO blog, Model model) {
        BlogVO findBlog = blogService.getUserBlog(blogService.getBlog(blog).getUser());
        List<CategoryVO> categorys = categoryService.getCategorys(findBlog);

        model.addAttribute("blog", findBlog);
        model.addAttribute("categorys", categorys);

        return "blogadmin_category";
    }

    @RequestMapping("/insertCategory")
    public String insertCategory(BlogVO blog, CategoryVO category, Model model) {
        BlogVO findBlog = blogService.getUserBlog(blogService.getBlog(blog).getUser());
        categoryService.insertCategory(category, findBlog);
        List<CategoryVO> categorys = categoryService.getCategorys(findBlog);

        model.addAttribute("blog", findBlog);
        model.addAttribute("categorys", categorys);

        return "blogadmin_category";
    }

    @RequestMapping("/deleteCategory")
    public String deleteCategory(BlogVO blog, CategoryVO category, Model model, HttpSession session) {
        BlogVO findBlog = blogService.getUserBlog(blogService.getBlog(blog).getUser());

        category.setBlog(findBlog);
        categoryService.deleteCategory(category);

        List<CategoryVO> categorys = categoryService.getCategorys(findBlog);

        model.addAttribute("blog", findBlog);
        model.addAttribute("categorys", categorys);

        return "blogadmin_category";
    }

    @RequestMapping("/updateCategoryView")
    public String updateCategoryView(BlogVO blog, CategoryVO category, Model model) {
        BlogVO findBlog = blogService.getUserBlog(blogService.getBlog(blog).getUser());
        List<CategoryVO> categorys = categoryService.getCategorys(findBlog);
        CategoryVO updateCategory = categoryService.getCategory(category);

        model.addAttribute("blog", findBlog);
        model.addAttribute("categorys", categorys);
        model.addAttribute("updateCategory", updateCategory);

        return "blogadmin_category";
    }

    @RequestMapping("/updateCategory")
    public String updateCategory(BlogVO blog, CategoryVO category, Model model) {
        BlogVO findBlog = blogService.getUserBlog(blogService.getBlog(blog).getUser());

        category.setBlog(findBlog);
        categoryService.updateCategory(category);

        List<CategoryVO> categorys = categoryService.getCategorys(findBlog);

        model.addAttribute("blog", findBlog);
        model.addAttribute("categorys", categorys);

        return "blogadmin_category";
    }

}
