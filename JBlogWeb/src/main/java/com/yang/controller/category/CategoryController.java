package com.yang.controller.category;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {

    @GetMapping("/blogAdminCategoryView")
    public String blogAdminCategoryView() {
        return "blogadmin_category";
    }

    @GetMapping("/blogAdminCategoryUpdateView")
    public String blogAdminCategoryUpdateView() {
        return "blogadmin_updatecategory";
    }
}
