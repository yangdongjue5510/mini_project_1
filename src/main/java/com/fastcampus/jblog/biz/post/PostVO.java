package com.fastcampus.jblog.biz.post;

import com.fastcampus.jblog.biz.category.CategoryVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostVO {
    private int postId;
    private String title;
    private String content;
    private Date createdDate;
    private CategoryVO category;

    public static PostVO entityToVO(Post post) {
        return PostVO.builder()
                .postId(post.getPostId())
                .category(CategoryVO.entityToVO(post.getCategory()))
                .title(post.getTitle())
                .content(post.getContent())
                .createdDate(post.getCreatedDate())
                .build();
    }

    public static List<PostVO> entityToVOList(List<Post> posts) {
        List<PostVO> postVOList = new ArrayList<>();

        for(Post post : posts) {
            PostVO postVO = PostVO.builder()
                            .postId(post.getPostId())
                            .category(CategoryVO.entityToVO(post.getCategory()))
                            .title(post.getTitle())
                            .content(post.getContent())
                            .createdDate(post.getCreatedDate())
                            .build();
            postVOList.add(postVO);
        }

        return postVOList;
    }

    public static Post voToEntity(PostVO postVO) {
        return Post.builder()
                .postId(postVO.getPostId())
                .category(CategoryVO.voToEntity(postVO.getCategory()))
                .title(postVO.getTitle())
                .content(postVO.getContent())
                .createdDate(postVO.getCreatedDate())
                .build();
    }

    public static List<Post> voToEntityList(List<PostVO> postVOList) {
        List<Post> posts = new ArrayList<>();

        for(PostVO postVO : postVOList) {
            Post post = Post.builder()
                    .postId(postVO.getPostId())
                    .category(CategoryVO.voToEntity(postVO.getCategory()))
                    .title(postVO.getTitle())
                    .content(postVO.getContent())
                    .createdDate(postVO.getCreatedDate())
                    .build();
            posts.add(post);
        }

        return posts;
    }
}
