package org.sebapresti.mediaservices.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.sebapresti.mediaservices.model.Comment;
import org.sebapresti.mediaservices.service.CommentService;

@Path("/")
public class CommentResource {

	CommentService cs = new CommentService();
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long messageId){
		return cs.getAllComments(messageId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getMessage(@PathParam("messageId") long messageId, 
								@PathParam("commentId") long commentId){
		return cs.getComment(messageId,commentId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") long messageId,Comment comment){
		return cs.addComment(messageId,comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId,
								@PathParam("commentId") long commentId,Comment comment){
		comment.setId(commentId);
		return cs.updateComment(messageId,comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messageId") long messageId,
									@PathParam("commentId") long commentId){
		cs.removeComment(messageId,commentId);
	}
	
	
	
	@GET
	public String test(){
		return "new sub resource";
	}
	
	@GET
	@Path("/{commentId}")
	public String test2(@PathParam("messageId") long messageId, //parent pathParam
			@PathParam("commentId") long commentId){
		return "method to return comment id="+commentId+", for messageId="+messageId;
	}
	
}
