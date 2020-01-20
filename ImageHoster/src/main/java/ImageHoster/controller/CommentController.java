package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;


    @RequestMapping("/image/{imageId}/{imageTitle}/comments")
    public String createComment(@PathVariable("imageId") Integer id, @PathVariable("imageTitle") String title,
                                @RequestParam("text") String text, Model model){

        Comment comment=new Comment();
        comment.setCreatedDate(new Date());
        Image image= imageService.getImageByTitle(id,title);
        comment.setImage(image);
        comment.setText(text);
        comment = commentService.createComment(comment);
        model.addAttribute("id",id);
        model.addAttribute("title",title);

        return "/images/image";

    }
}
