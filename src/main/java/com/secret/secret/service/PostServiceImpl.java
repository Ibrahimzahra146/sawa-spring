package com.secret.secret.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secret.secret.model.Friend;
import com.secret.secret.model.Notification;
import com.secret.secret.model.Post;
import com.secret.secret.model.React;
import com.secret.secret.model.User;
import com.secret.secret.model.UserDeviceId;
import com.secret.secret.model.YoutubeLink;
import com.secret.secret.repository.CommentRepository;
import com.secret.secret.repository.NotificationRepository;
import com.secret.secret.repository.PostRepository;
import com.secret.secret.repository.ReactRepository;
import com.secret.secret.repository.YoutubeLinkRepository;
import com.secret.secret.request.PostModel;
import com.secret.secret.response.PostCommentResponse;
import com.secret.secret.response.ReactListModel;
import com.secret.secret.response.ReactSingleModel;
import com.secret.secret.utils.NotificationUtil;
import com.secret.secret.utils.YoutubeUtil;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	LoginService loginService;
	@Autowired
	PostRepository postRep;
	@Autowired
	ReactRepository reactRep;
	@Autowired
	YoutubeLinkRepository youtubeLinkRep;
	@Autowired
	CommentRepository commentRep;
	@Autowired
	FriendService friendService;
	@Autowired
	NotificationRepository notificationRep;
	@Autowired
	UserDeviceIdService userDeviceIdService;
	@Autowired
	DeviceTokenService deviceTokenService;
	@Autowired
	EntityManager entityManger;

	@Override
	public Post addNewPost(PostModel postModel, String image) throws Exception {
		Post post = new Post();
		User user = loginService.findUserById(postModel.getUserId());
		post.setUserId(user);
		post.setText(postModel.getText());
		if (image.equals("")) {
			image = null;
		}
		System.out.println("postModel.getIs_public_comment())"+postModel.getIs_public_comment());
		post.setIsPublicComment(postModel.getIs_public_comment());
		post.setLink(postModel.getLink());
		System.out.println("postModel.getIs_public_comment())"+post.getIsPublicComment());

		// if(postModel.getLink().equals("")){
		// image=null;
		// }
		YoutubeUtil youtubeUtil = new YoutubeUtil();

		if (postModel.getLink().equals("")) {
			YoutubeLink youtubeLink = new YoutubeLink();
			youtubeLink.setId(2);
			youtubeLink.setAuthor_name("");
			youtubeLink.setImage("");
			youtubeLink.setTitle("");
			youtubeLink.setLink("");
			post.setYoutubeLink(youtubeLink);

		} else {
			YoutubeLink youtubeLink = youtubeLinkRep.findYoutube(postModel.getLink());
			youtubeLink = youtubeLinkRep.findYoutube(postModel.getLink());
			if (youtubeLink == null) {
				youtubeLink = youtubeUtil.getVideoInfo(postModel.getLink());

			}
			// youtubeLinkRep.save(youtubeLink);
			post.setYoutubeLink(youtubeLinkRep.save(youtubeLink));
		}

		post.setImage(image);
		post.setTimestamp("");
		post = postRep.save(post);
		return post;
		// Friend friend=new Friend();
		// friend.setFriend1_id(user);
		// friend.setFriend2_id(toUser);
		// //friend.setFriend1_state(0);
		// post=postRep.save(post);
		// Notification notification=new Notification();
		// notification.setFriend1_id(user);
		// notification.setFriend2_id(toUser);
		// notification.setFriendshipId(friendService.findFirstFriend());
		//
		// notification.setPostId(post);
		// notification.setType(3);
		// notification.setSent_flag(0);
		// notification.setRead_flag(0);
		// notificationRep.save(notification);
		// List<UserDeviceId>
		// userDeviceId=userDeviceIdService.findAllDeviceIdByUserId(postModel.getToUserId());
		// String deviceId=userDeviceId.get(0).getDeviceId().getDeviceId();
		// String token
		// =deviceTokenService.findDeviceToken(deviceId).getToken();
		// NotificationUtil notificationUtil=new NotificationUtil();
		//// try {
		//// notificationUtil.sendPushNotification(token);
		//// } catch (IOException e) {
		//// // TODO Auto-generated catch block
		//// e.printStackTrace();
		//// } catch (JSONException e) {
		//// // TODO Auto-generated catch block
		//// e.printStackTrace();
		//// }

	}

	@Override
	public Post findPostById(int postId) {
		// TODO Auto-generated method stub
		return postRep.findPostById(postId);
	}

	public Post findFirstPost() {
		return postRep.findFirstPost();
	}

	@Override
	public List<PostCommentResponse> getUserPost(int id, boolean isPublicComment) {
		// TODO Auto-generated method stub
		boolean myLikeAction = false;
		boolean myDisLikeAction = false;
		boolean myLoveAction = false;
		List<Post> postList = new ArrayList<Post>();
		React react = new React();
		postList = postRep.getUserPost(id);
		List<User> users = new ArrayList<User>();
		List<PostCommentResponse> postCommentResponse = new ArrayList<PostCommentResponse>();
		for (int i = 0; i < postList.size(); i++) {
			myLikeAction = false;
			myDisLikeAction = false;
			myLoveAction = false;
			ReactSingleModel reactSingleModel = new ReactSingleModel();

			PostCommentResponse temp = new PostCommentResponse();
			temp.setPost(postList.get(i));
			// Chwck if the user take an action for this post
			react = reactRep.findReactRecord(postList.get(i).getPostId(), id);
			if (react != null) {
				if (react.getType() == 1) {
					myLikeAction = true;
				}
				if (react.getType() == 2) {
					myDisLikeAction = true;

				} else if (react.getType() == 3) {
					myLoveAction = true;

				}
			}
			temp.setComments(commentRep.findPostComment(postList.get(i).getPostId()));
			ReactListModel reactList = new ReactListModel();
			users = reactRep.findPostDisLikes(postList.get(i).getPostId());

			reactSingleModel.setCount(users.size());
			reactSingleModel.setUsers(users);
			reactSingleModel.setMyAction(myDisLikeAction);

			reactList.setDislikeList(reactSingleModel);
			//
			reactSingleModel = new ReactSingleModel();


			users = reactRep.findPostLikes(postList.get(i).getPostId());
			System.out.println("postList.get(i).getPostId()" + users.size());

			reactSingleModel.setCount(users.size());
			reactSingleModel.setUsers(users);
			reactSingleModel.setMyAction(myLikeAction);
			reactList.setLikeList(reactSingleModel);
			//
			reactSingleModel = new ReactSingleModel();
			users = reactRep.findPostLoves(postList.get(i).getPostId());
			reactSingleModel.setCount(users.size());
			reactSingleModel.setUsers(users);
			reactSingleModel.setMyAction(myLoveAction);

			reactList.setLoveList(reactSingleModel);
			//
			temp.setReacts(reactList);
			postCommentResponse.add(temp);

			//
		}
		return postCommentResponse;
	}

	@Override
	public List<PostCommentResponse> getUserHomePost(int id) {
		boolean myLikeAction = false;
		boolean myDisLikeAction = false;
		boolean myLoveAction = false;
		List<Post> postList = new ArrayList<Post>();
		postList = postRep.getUserHomePost(id);
		List<User> users = new ArrayList<User>();
		List<PostCommentResponse> postCommentResponse = new ArrayList<PostCommentResponse>();
		for (int i = 0; i < postList.size(); i++) {
			myLikeAction = false;
			myDisLikeAction = false;
			myLoveAction = false;
			ReactSingleModel reactSingleModel = new ReactSingleModel();
			ReactListModel reactList = new ReactListModel();
			React react = new React();

			PostCommentResponse temp = new PostCommentResponse();
			temp.setPost(postList.get(i));
			temp.setComments(commentRep.findPostComment(postList.get(i).getPostId()));

			react = reactRep.findReactRecord(postList.get(i).getPostId(), id);
			if (react != null) {
				if (react.getType() == 1) {
					myLikeAction = true;
				}
				if (react.getType() == 2) {
					myDisLikeAction = true;

				} else if (react.getType() == 3) {
					myLoveAction = true;

				}
			}
			users = reactRep.findPostDisLikes(postList.get(i).getPostId());
			reactSingleModel.setCount(users.size());
			reactSingleModel.setUsers(users);
			reactSingleModel.setMyAction(myDisLikeAction);

			reactList.setDislikeList(reactSingleModel);
			//
			reactSingleModel = new ReactSingleModel();
			System.out.println("postList.get(i).getPostId()" + postList.get(i).getPostId());

			users = reactRep.findPostLikes(postList.get(i).getPostId());
			System.out.println("postList.get(i).getPostId()" + users.size());

			reactSingleModel.setCount(users.size());
			reactSingleModel.setUsers(users);
			reactSingleModel.setMyAction(myLikeAction);

			reactList.setLikeList(reactSingleModel);
			//
			reactSingleModel = new ReactSingleModel();
			users = reactRep.findPostLoves(postList.get(i).getPostId());
			reactSingleModel.setCount(users.size());
			reactSingleModel.setUsers(users);
			reactSingleModel.setMyAction(myLoveAction);

			reactList.setLoveList(reactSingleModel);
			//
			temp.setReacts(reactList);
			postCommentResponse.add(temp);

			//
		}
		return postCommentResponse;
		// TODO Auto-generated method stub

	}

	@Override
	public PostCommentResponse getPostWithComments(int postId) {
		// TODO Auto-generated method stub
		PostCommentResponse temp = new PostCommentResponse();

		temp.setPost(postRep.findPostById(postId));
		temp.setComments(commentRep.findPostComment(postId));
		return temp;
	}

	@Override
	public List<PostCommentResponse> getRandomPosts(int page) {
		List<Post> postList = new ArrayList<Post>();
	
		List<PostCommentResponse> postCommentResponse = new ArrayList<PostCommentResponse>();
		List<User> users = new ArrayList<User>();

		int startPost = page;
		int numberOfSelectedRecords = 100;
		if (page > 10) {
			numberOfSelectedRecords = 200;
			startPost = 100+page;
		}
		//postList = postRep.getRandomPosts(page);
		Query p = this.entityManger.createQuery("SELECT p from Post p",Post.class).setMaxResults(numberOfSelectedRecords);
		postList=p.getResultList();
		for (int i = startPost; i < postList.size(); i=i+10) {
			System.out.println("Post id "+postList.get(i).getPostId());
			System.out.println("iteration # "+i);

			PostCommentResponse temp = new PostCommentResponse();
			ReactSingleModel reactSingleModel = new ReactSingleModel();
			ReactListModel reactList = new ReactListModel();
			Post post=postList.get(i);
			if(post!=null){
				
			
			temp.setPost(postList.get(i));
			temp.setComments(commentRep.findPostComment(post.getPostId()));
			users = reactRep.findPostDisLikes(post.getPostId());
			reactSingleModel.setCount(users.size());
			reactSingleModel.setUsers(users);
			reactList.setDislikeList(reactSingleModel);
			reactSingleModel = new ReactSingleModel();
			
			users = reactRep.findPostLikes(post.getPostId());

			reactSingleModel.setCount(users.size());
			reactSingleModel.setUsers(users);

			reactList.setLikeList(reactSingleModel);
			//
			reactSingleModel = new ReactSingleModel();
			users = reactRep.findPostLoves(post.getPostId());
			reactSingleModel.setCount(users.size());
			reactSingleModel.setUsers(users);

			reactList.setLoveList(reactSingleModel);
			//
			temp.setReacts(reactList);
			postCommentResponse.add(temp);
			}
		}
		// TODO Auto-generated method stub
		return postCommentResponse;
	}

}
