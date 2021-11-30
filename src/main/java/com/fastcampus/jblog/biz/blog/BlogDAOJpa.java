package com.fastcampus.jblog.biz.blog;

import com.fastcampus.jblog.biz.user.UserRepository;
import com.fastcampus.jblog.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BlogDAOJpa implements BlogDAO {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public BlogVO getUserBlog(UserVO userVO) {
        Blog blog = blogRepository.findByUserId(userVO.getUserId());
        if(blog == null) {
            return null;
        }
        return BlogVO.entityToVO(blog);
    }

    @Override
    public List<BlogVO> getBlogs() {
        return BlogVO.entityToVOList(blogRepository.findAll());
    }

    @Override
    public BlogVO getBlog(BlogVO blogVO) {
        return BlogVO.entityToVO(blogRepository.findById(blogVO.getBlogId()).get());
    }

    @Override
    public List<BlogVO> searchBlogByTitle(SearchVO searchVO) {
        return BlogVO.entityToVOList(blogRepository.findByTitle(searchVO.getSearchValue()));
    }

    @Override
    public List<BlogVO> searchBlogByTag(SearchVO searchVO) {
        return BlogVO.entityToVOList(blogRepository.findByTag(searchVO.getSearchValue()));
    }

    @Override
    public List<BlogVO> searchBlogByUser(SearchVO searchVO) {
        return BlogVO.entityToVOList(blogRepository.findByUser(searchVO.getSearchValue()));
    }

    @Override
    public void createBlog(BlogVO blogVO) {
        blogRepository.save(BlogVO.voToEntity(blogVO));
    }

    @Override
    public void updateBlog(BlogVO blogVO) {
        Blog blog = BlogVO.voToEntity(blogVO);
        blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(BlogVO blogVO) {
        blogRepository.deleteById(blogVO.getBlogId());
    }

    @Override
    public void deleteRequest(BlogVO blogVO) {
        Blog blog = blogRepository.findById(blogVO.getBlogId()).get();
        blog.setStatus("삭제요청");
        blogRepository.save(blog);
    }
}
