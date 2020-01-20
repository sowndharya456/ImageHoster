package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;


    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") Integer id, @PathVariable("imageTitle") String title,
                                @RequestParam("comment") String text, Model model, HttpSession session) {

        Comment comment = new Comment();
        comment.setCreatedDate(new Date());
        Image image = imageService.getImage(id);
        comment.setImage(image);
        comment.setText(text);
        User user = (User) session.getAttribute("loggeduser");
        comment.setUser(user);

        model.addAttribute("id", id);
        model.addAttribute("title", title);
        commentService.createComment(comment);
        List<Comment> commentList = new ArrayList<>();
        if (image != null) {
            commentList = image.getComments();
        }
        model.addAttribute("comments", commentList);
        model.addAttribute("image", image);
        return "redirect:/images/" + id + "/" + title;

    }
}
