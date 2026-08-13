package ir.ac.kntu.userinterface;

import ir.ac.kntu.notification.*;
import ir.ac.kntu.support.TicketProcessor;
import ir.ac.kntu.user.ChatPage;
import ir.ac.kntu.user.Message;
import ir.ac.kntu.user.Post;
import ir.ac.kntu.user.User;
import ir.ac.kntu.user.service.UserService;
import ir.ac.kntu.user.service.UserServiceImplementation;

import java.time.LocalDateTime;
import java.util.*;

public class PagePrinter {
    private UserService userService = new UserServiceImplementation();
    private TicketProcessor ticketProcessor = new TicketProcessor();
    private final ManagerPagePrinter managerPagePrinter = new ManagerPagePrinter();
    private Scanner scanner = new Scanner(System.in);
    private String choice;
    private SupportPagePrinter supportPagePrinter = new SupportPagePrinter();
//    public PagePrinter() throws Exception {
//        User user1 = new User("pedram", "258153@#$Pdrm", "awe@fs.as", "Pedram", "monazami");
//        User user2 = new User("parham", "258153@#$Pdrm", "awe@fssd.as", "Parham", "monazami");
//        userService.addUser(user1);
//        userService.addUser(user2);
//        user1.getProfile().setPrivate(true);
//        user2.getProfile().setPrivate(true);
//        user1.getProfile().getFollowing().add(user2);
//        user2.getProfile().getFollowing().add(user1);
//        user1.getProfile().getFollowers().add(user2);
//        user2.getProfile().getFollowers().add(user1);
//        userService.makePost("post1", user2);
//        userService.makePost("post2", user2);
//        userService.makePost("post3", user2);
//        userService.makePost("post4", user2);
//        userService.likePost(user2.getProfile().getPosts().get(0), user1);
//        userService.likePost(user2.getProfile().getPosts().get(0), user1);
//        userService.likePost(user2.getProfile().getPosts().get(0), user1);
//        userService.likePost(user2.getProfile().getPosts().get(2), user1);
//        userService.likePost(user2.getProfile().getPosts().get(1), user1);
//        userService.likePost(user2.getProfile().getPosts().get(1), user1);
//        userService.likePost(user2.getProfile().getPosts().get(1), user1);
//        userService.likePost(user2.getProfile().getPosts().get(1), user1);
//        userService.likePost(user2.getProfile().getPosts().get(3), user1);
//        userService.likePost(user2.getProfile().getPosts().get(3), user1);
//        userService.likePost(user2.getProfile().getPosts().get(3), user1);
//        userService.commentOnPost(user2.getProfile().getPosts().get(0), "ahhahadbsdas");
//        userService.commentOnPost(user2.getProfile().getPosts().get(1), "ahhahadbsdas");
//        userService.commentOnPost(user2.getProfile().getPosts().get(1), "ahhahadbsdas");
//        userService.commentOnPost(user2.getProfile().getPosts().get(2), "ahhahadbsdas");
//        userService.commentOnPost(user2.getProfile().getPosts().get(2), "ahhahadbsdas");
//        userService.commentOnPost(user2.getProfile().getPosts().get(2), "ahhahadbsdas");
//        userService.commentOnPost(user2.getProfile().getPosts().get(2), "ahhahadbsdas");
//        userService.commentOnPost(user2.getProfile().getPosts().get(0), "ahhahadbsdas");
//        userService.commentOnPost(user2.getProfile().getPosts().get(0), "ahhahadbsdas");
//    }

    public void landingPage() {
        System.out.println("""
                please select what you would like to do.
                1) login
                2) register
                3) exit""");
        choice = scanner.nextLine();
        try {
            // try to parse choice, if invalid string is input, go to catch
            switch (Integer.parseInt(choice)) {
                case 1 -> loginPage();
                case 2 -> registerPage();
                case 3 -> System.exit(0);
                default -> {
                    System.out.println("enter 1 or 2");
                    landingPage();
                }
            }
        } catch (Exception e) {
            System.out.println("please enter a number!");
            landingPage();
        }
    }

    public void registerPage() {
        System.out.println("please enter your username: ");
        String username = scanner.nextLine();
        System.out.println("please enter your password: ");
        String password = scanner.nextLine();
        System.out.println("please enter your email: ");
        String email = scanner.nextLine();
        System.out.println("please enter your name: ");
        String name = scanner.nextLine();
        System.out.println("please enter your lastname: ");
        String lastname = scanner.nextLine();
        User user = new User(username, password, email, name, lastname);
        try {
            userService.addUser(user);
            System.out.println("user added! you can now login, redirecting you to login page!");
            loginPage();
        } catch (Exception e) {
            System.out.println("registration failed, because " + e.getMessage());
            System.out.println("""
                    1) try again
                    2) go to welcome page""");
            choice = scanner.nextLine();
            try {
                // try to parse choice, if invalid string is input, go to catch
                switch (Integer.parseInt(choice)) {
                    case 1 -> registerPage();
                    case 2 -> landingPage();
                    default -> {
                        System.out.println("invalid input, going back to landing page");
                        landingPage();
                    }
                }
            } catch (Exception ex) {
                System.out.println("invalid input!, going back to landing page");
                landingPage();
            }
        }
    }

    public void loginPage() {
        System.out.println("""
                Please select your role:
                1) Normal user
                2) Admin
                3) Manager""");
        choice = scanner.nextLine();
        try {
            // try to parse choice, if invalid string is input, go to catch
            switch (Integer.parseInt(choice)) {
                case 1 -> {}
                case 2 -> supportPagePrinter.supportLogin();
                case 3 -> managerPagePrinter.managerLogin();
                default -> {
                    System.out.println("invalid input, going back to landing page");
                    landingPage();
                }
            }
        } catch (Exception ex) {
            System.out.println("invalid input!, going back to landing page");
            landingPage();
        }
        System.out.println("please enter your username! :");
        String username = scanner.nextLine();
        System.out.println("please enter your password:");
        String password = scanner.nextLine();
        try {
            User user = userService.login(username, password);
            System.out.println("user logged in!, loading your dashboard");
            dashboard(user);

        } catch (Exception e) {
            System.out.println("login failed, because " + e.getMessage());
            System.out.println("""
                    1) try again
                    2) go to landing page""");
            choice = scanner.nextLine();
            try {
                // try to parse choice, if invalid string is input, go to catch
                switch (Integer.parseInt(choice)) {
                    case 1 -> loginPage();
                    case 2 -> landingPage();
                    default -> {
                        System.out.println("invalid input, going back to landing page");
                        landingPage();
                    }
                }
            } catch (Exception ex) {
                System.out.println("invalid input!, going back to login page");
                loginPage();
            }
        }

    }

    public void dashboard(User user) {
        System.out.println("""
                what would you like to do?
                1) profile management
                2) post management
                3) users
                4) messages
                5) support
                6) home page
                7) notifications
                8) settings
                9) exit to landing page""");
        choice = scanner.nextLine();
        try {
            // try to parse choice, if invalid string is input, go to catch
            switch (Integer.parseInt(choice)) {
                case 1 -> profileManagement(user);
                case 2 -> postManagement(user);
                case 3 -> users(user);
                case 4 -> messagesMenu(user);
                case 5 -> support(user);
                case 6 -> home(user, new ArrayList<>(), "default");
                case 7 -> notifications(user);
                case 8 -> settings(user);
                case 9 -> landingPage();
                default -> {
                    System.out.println("enter 1 to 7");
                    dashboard(user);
                }
            }
        } catch (Exception ex) {
            System.out.println("invalid input!, going back to dashboard");
            dashboard(user);
        }
    }

    public void notifications(User user) {
        System.out.println("\nWelcome to notifications panel");
        if (user.getProfile().getNotifications().isEmpty()) {
            System.out.println("There is no new notifications");
        } else {
            int i = 1;
            for (Notification notification : user.getProfile().getNotifications()) {
                if (notification instanceof GeneralNotification) {
                    if(((GeneralNotification) notification).isActive() && !((GeneralNotification) notification).getReadByUsers().contains(user.getUsername())) {
                        System.out.println(i + ") " + notification.getNotificationText());
                    }
                } else {
                    System.out.println(i + ") " + notification.getNotificationText());
                }
            }
            System.out.println("\nEnter index of notification you want to see or -1 to go back to dashboard");
            choice = scanner.nextLine();
            if (choice.equals("-1")) {
                dashboard(user);
            }
            Notification selectedNotification = user.getProfile().getNotifications().get(Integer.parseInt(choice) - 1);
            if (!(selectedNotification instanceof GeneralNotification)) {
                user.getProfile().getNotifications().remove(selectedNotification);
            }
            selectedNotification.handleSelection(new PagePrinter(), user);
        }
    }

    public void messagesMenu(User user) {
        int index = 1;
        System.out.println("Welcome to the messages page\n");
        for (User following : user.getProfile().getFollowing()) {
            System.out.println(index + ") " + following.getUsername());
            index++;
        }
        try {
            System.out.println("Enter index of user you want to start chat or 'quit' to go back to dashboard:");
            choice = scanner.nextLine();
            if (choice.equals("quit")) {
                dashboard(user);
            }
            User target_user = user.getProfile().getFollowing().get(Integer.parseInt(choice) - 1);
            if (!target_user.getProfile().getFollowing().contains(user)) {
                System.out.println(target_user.getUsername() + " should follow you before you start a chat with");
                messagesMenu(user);
            }

            List<ChatPage> user_chatPages = user.getProfile().getChatPages();
            ChatPage chat = null;
            for (ChatPage chatPage : user_chatPages) {
                if (chatPage.getUsers().contains(target_user)) {
                    chat = chatPage;
                    break;
                }
            }

            if (chat == null) {
                chat = new ChatPage();
                chat.addUsers(user, target_user);
                user.getProfile().addChatPage(chat);
                target_user.getProfile().addChatPage(chat);
            }
            NewMessageNotification notification = new NewMessageNotification();
            notification.setSenderUsername(user.getUsername());
            notification.setChatPage(chat);
            target_user.getProfile().addNotification(notification);
            chatPageViewer(user, chat);
        } catch (Exception e) {
            System.out.println("invalid input, try again...\n");
            messagesMenu(user);
        }
    }

    public void chatPageViewer(User user, ChatPage chat) {
        User target_user;
        if (Objects.equals(user, chat.getUsers().get(0))) {
            target_user = chat.getUsers().get(1);
        } else {
            target_user = chat.getUsers().get(0);
        }
        System.out.println("Chat between " + chat.getParticipants());
        chat.printMessages();
        System.out.println("Type new message to " + target_user.getUsername() + " or 'exit_chat' to go back to messages page");
        String input = scanner.nextLine();
        if (!input.equals("exit_chat")) {
            LocalDateTime timestamp = LocalDateTime.now();
            Message new_message = new Message(user.getUsername(), input, timestamp);
            chat.addMessage(new_message);
            chatPageViewer(user, chat);
        }
        messagesMenu(user);
    }

    public void home(User user, List<Post> postList, String state) {
        System.out.println("welcome to the home page");
        int index = 1;
        if (postList.isEmpty() && state.equals("default")) {
            for (User following : user.getProfile().getFollowing()) {
                for (Post post : following.getProfile().getPosts()) {
                    System.out.println(index +") " + post.getText() + " / number of likes: " + post.getLikers().size());
                    postList.add(post);
                    index++;
                }
            }
        } else {
            for (Post post : postList) {
                System.out.println(index +") " + post.getText() + " / number of likes: " + post.getLikers().size());
                index++;
            }
        }

        System.out.println("enter the index of the post you would like to interact with or enter -1 to go to dashboard or 0 to go to filter pages");
        try {
            choice = scanner.nextLine();
            if (choice.equals("-1")) dashboard(user);
            if (choice.equals("0")) {
                System.out.println("""
                        1) date (newest to oldest)
                        2) date (oldest to newest
                        3) like (Ascending)
                        4) like (descending)
                        5) comment (Ascending)
                        6) comment (descending)
                        7) only those whom follow you""");
                String filter_choice = scanner.nextLine();
                switch (filter_choice) {
                    case "1" -> postList.sort(Comparator.comparing(Post::getPostDate));
                    case "2" -> postList.sort(Comparator.comparing(Post::getPostDate).reversed());
                    case "3" -> postList.sort(Comparator.comparingInt(post -> post.getLikers().size()));
                    case "4" -> postList.sort(Comparator.<Post>comparingInt(post -> post.getLikers().size()).reversed());
                    case "5" -> postList.sort(Comparator.comparingInt(post -> post.getComments().size()));
                    case "6" -> postList.sort(Comparator.<Post>comparingInt(post -> post.getComments().size()).reversed());
                    case "7" -> {
                        postList = new ArrayList<>();
                        for (User following : user.getProfile().getFollowing()) {
                            if (user.getProfile().getFollowers().contains(following)) {
                                postList.addAll(following.getProfile().getPosts());
                            }
                        }
                    }
                    default -> {
                        System.out.println("Please enter 1 to 7. Going back to home page");
                        home(user, new ArrayList<>(), "default");
                    }
                }
                home(user, postList, "filtered");
            }
            int selectedIndex = Integer.parseInt(choice) - 1;
            interactWithPost(user, postList.get(selectedIndex));
        } catch (Exception e) {
            System.out.println("invalid input!");
            home(user, new ArrayList<>(), "default");
        }
    }

    public void interactWithPost(User user, Post post) {
        System.out.println("post text is: " + post.getText());
        System.out.println("""
                what would you like to do?
                1) like post
                2) comment on this post
                3) go back to home""");
        choice = scanner.nextLine();
        try {
            // try to parse choice, if invalid string is input, go to catch
            switch (Integer.parseInt(choice)) {
                case 1 -> {
                    userService.likePost(post, user);
                    LikeNotification notification = new LikeNotification();
                    notification.setPost(post);
                    notification.setUsername(user.getUsername());
                    user.getProfile().addNotification(notification);
                }
                case 2 -> {
                    System.out.println("enter your comment: ");
                    String comment = scanner.nextLine();
                    userService.commentOnPost(post, comment);
                    CommentNotification notification = new CommentNotification();
                    notification.setPost(post);
                    notification.setUsername(user.getUsername());
                    notification.setCommentText(comment);
                    user.getProfile().addNotification(notification);
                }
                case 3 -> home(user, new ArrayList<>(), "default");
                default -> {
                    System.out.println("enter 1 to 3");
                    interactWithPost(user, post);
                }
            }
        } catch (Exception ex) {
            System.out.println("invalid input!");
            interactWithPost(user, post);
        }

    }

    public void settings(User user) {
        System.out.println("""
                welcome to settings, what would you like to do?
                1) change password
                2) make page private or public
                3) change username
                4) go back to dashboard""");
        choice = scanner.nextLine();
        try {
            // try to parse choice, if invalid string is input, go to catch
            switch (Integer.parseInt(choice)) {
                case 1 -> {
                    System.out.println("enter your new password: ");
                    String newPassword = scanner.nextLine();
                    user.setPassword(newPassword);
                    System.out.println("operation successful!");
                    settings(user);
                }
                case 2 -> {
                    System.out.println("your page is currently ");
                    if (user.getProfile().isPrivate()) System.out.println("private");
                    else System.out.println("public");
                    System.out.println("enter 1 to change your privacy, or enter anything else to go back");
                    String change = scanner.nextLine();
                    if (change.equals("1")) {
                        user.getProfile().setPrivate(!user.getProfile().isPrivate());
                        System.out.println("operation successful!");
                        settings(user);
                    } else settings(user);
                }
                case 3 -> {
                    System.out.println("enter your new username: ");
                    String newUsername = scanner.nextLine();
                    if (!userService.isUsernameDuplicated(newUsername)) {
                        user.setUsername(newUsername);
                    }
                    else System.out.println("username already exits!");
                    settings(user);
                }
                case 4 -> dashboard(user);
                default -> {
                    System.out.println("enter 1 to 3");
                    settings(user);
                }
            }
        } catch (Exception ex) {
            System.out.println("invalid input!");
            settings(user);
        }

    }

    public void support(User user) {
        System.out.println("""
                welcome to support, what would you like to do?
                1) make report
                2) users support
                3) setting support
                4) view support alerts
                5) go back to dashboard""");
        choice = scanner.nextLine();
        try {
            // try to parse choice, if invalid string is input, go to catch
            switch (Integer.parseInt(choice)) {
                case 1 -> profileManagement(user);
                case 2 -> postManagement(user);
                case 3 -> users(user);
                case 4 -> support(user);
                case 5 -> dashboard(user);
                default -> {
                    System.out.println("enter 1 to 5");
                    support(user);
                }
            }
        } catch (Exception ex) {
            System.out.println("invalid input!, going back to dashboard");
            dashboard(user);
        }
    }

    public void users(User user) {
        System.out.println("""
                welcome to users, what would you like to do?
                1) see followers list
                2) see following list
                3) see follow requests
                4) search users
                5) go back to dashboard""");
        choice = scanner.nextLine();
        try {
            // try to parse choice, if invalid string is input, go to catch
            switch (Integer.parseInt(choice)) {
                case 1 -> followers(user);
                case 2 -> following(user);
                case 3 -> followRequest(user);
                case 4 -> search(user);
                case 5 -> dashboard(user);
                default -> {
                    System.out.println("enter 1 to 4");
                    users(user);
                }
            }
        } catch (Exception ex) {
            System.out.println("invalid input!, going back");
            users(user);
        }
    }

    public void search(User user) {
        System.out.println("""
                enter the username of the user you want to search or zero to go back:""");
        String username = scanner.nextLine();
        try {
            if (username.equals("0")) users(user);
            User searchedUser = userService.getUser(username);
            goToSearchedProfile(searchedUser, user);
        } catch (Exception e) {
            System.out.println("no such user, going back to search");
        }
    }

    public void goToSearchedProfile(User searchedUser, User originalUser) {
        if (searchedUser.getProfile().isPrivate()) {
            System.out.println(searchedUser.getUsername());
            System.out.println(searchedUser.getProfile().getBio());
            System.out.println("this user is private, to see full info and posts, enter 1 to send a follow request or enter anything else to go back");
            choice = scanner.nextLine();
            if (choice.equals("1")) {
                searchedUser.getProfile().getFollowRequests().add(originalUser);
                System.out.println("follow request sent, going back to users");
                users(originalUser);
            } else {
                users(originalUser);
            }
        }
        // if profile is public
        System.out.println(searchedUser.getUsername());
        System.out.println(searchedUser.getProfile().getBio());
        System.out.println("===============================================");
        for (int postIdLoop = 1; postIdLoop <= searchedUser.getProfile().getPosts().size(); postIdLoop++) {
            System.out.println(postIdLoop + ") " + searchedUser.getProfile().getPosts().get(postIdLoop - 1).getText());
            System.out.println("number of likes: " + searchedUser.getProfile().getPosts().get(postIdLoop - 1).getLikers().size());
        }
        System.out.println("enter anything to go back to search");
        choice = scanner.nextLine();
        search(originalUser);
    }

    public void followRequest(User user) {
        if (!user.getProfile().isPrivate()) {
            System.out.println("your profile is not private, to access this page, make your profile private");
            users(user);
        }
        for (int followReqIndex = 1; followReqIndex <= user.getProfile().getFollowRequests().size(); followReqIndex++) {
            System.out.println(followReqIndex + ") " + user.getProfile().getFollowRequests().get(followReqIndex - 1).getUsername());
        }
        System.out.println("enter the index of the follow request you would like to accept or -1 to go back");
        try {
            choice = scanner.nextLine();
            int followingIndex = Integer.parseInt(choice);
            if (followingIndex == -1) users(user);
            userService.followUser(user, user.getProfile().getFollowRequests().get(followingIndex - 1));
            System.out.println("follow request accepted!");
            users(user);
        } catch (Exception e) {
            System.out.println("invalid input!, going back to users");
            users(user);
        }
    }

    public void following(User user) {
        for (int followingIndex = 1; followingIndex <= user.getProfile().getFollowers().size(); followingIndex++) {
            System.out.println(followingIndex + ") " + user.getProfile().getFollowing().get(followingIndex - 1).getName());
        }
        System.out.println("enter the index of the following you would like to remove or -1 to go back");
        try {
            choice = scanner.nextLine();
            int followingIndex = Integer.parseInt(choice);
            if (followingIndex == -1) users(user);
            userService.unfollowUser(user, user.getProfile().getFollowers().get(followingIndex-1));
            System.out.println("following removed!");
            users(user);
        } catch (Exception e) {
            System.out.println("invalid input!, going back to users");
            users(user);
        }
    }

    public void followers(User user) {
        for (int followerIndex = 1; followerIndex <= user.getProfile().getFollowers().size(); followerIndex++) {
            System.out.println(followerIndex + ") " + user.getProfile().getFollowers().get(followerIndex - 1).getName());
        }
        System.out.println("enter the index of the follower you would like to remove or -1 to go back");
        try {
            choice = scanner.nextLine();
            int followerIndex = Integer.parseInt(choice);
            if (followerIndex == -1) users(user);
            userService.unfollowUser(user.getProfile().getFollowers().get(followerIndex - 1), user);
            System.out.println("follower removed!");
            users(user);
        } catch (Exception e) {
            System.out.println("invalid input!, going back to users");
            users(user);
        }
    }

    public void postManagement(User user) {
        System.out.println("""
                welcome to post management, what would you like to do?
                1) make new post
                2) view your posts
                3) go back to dashboard""");
        choice = scanner.nextLine();
        try {
            // try to parse choice, if invalid string is input, go to catch
            switch (Integer.parseInt(choice)) {
                case 1 -> {
                    System.out.println("enter your post text:");
                    String postText = scanner.nextLine();
                    userService.makePost(postText, user);
                    System.out.println("operation successful!, redirecting to postManagement");
                    postManagement(user);
                }
                case 2 -> viewPosts(user);
                case 3 -> dashboard(user);
                default -> {
                    System.out.println("enter 1 to 3");
                    postManagement(user);
                }
            }
        } catch (Exception ex) {
            System.out.println("invalid input!, going back to post management");
            postManagement(user);
        }
    }

    public void viewPosts(User user) {
        for (int postIdLoop = 0; postIdLoop < user.getProfile().getPosts().size(); postIdLoop++) {
            System.out.println(postIdLoop + user.getProfile().getPosts().get(postIdLoop).getText());
            System.out.println("number of likes: " + user.getProfile().getPosts().get(postIdLoop).getLikers().size());
        }
        System.out.println("if you want to edit or delete a post, enter its index:");
        choice = scanner.nextLine();
        try {
            int postIndex = Integer.parseInt(choice);
            Post post = user.getProfile().getPosts().get(postIndex);
            editOrDeletePost(post, user);
        } catch (Exception e) {
            System.out.println("invalid index, going back to view posts again!");
            viewPosts(user);
        }
    }

    public void editOrDeletePost(Post post, User user) {
        System.out.println("""
                what would you like to do?
                1) edit post
                2) delete post
                3) go back to post management""");
        choice = scanner.nextLine();
        try {
            // try to parse choice, if invalid string is input, go to catch
            switch (Integer.parseInt(choice)) {
                case 1 -> editPost(post, user);
                case 2 -> {
                    user.getProfile().getPosts().remove(post);
                    System.out.println("operation successful!, redirecting to postManagement");
                    postManagement(user);
                }
                case 3 -> postManagement(user);
                default -> {
                    System.out.println("enter 1 to 3");
                    editOrDeletePost(post, user);
                }
            }
        } catch (Exception ex) {
            System.out.println("invalid input!, going back!");
            editOrDeletePost(post, user);
        }
    }

    public void editPost(Post post, User user) {
        System.out.println("""
                what would you like to do?
                1) edit post
                2) remove comments
                3) go back to post management""");
        try {
            // try to parse choice, if invalid string is input, go to catch
            switch (Integer.parseInt(choice)) {
                case 1 -> {
                    System.out.println("enter your new post text:");
                    String postText = scanner.nextLine();
                    post.setText(postText);
                    System.out.println("operation successful!, redirecting to postManagement");
                    postManagement(user);
                }
                case 2 -> removeComment(user, post);
                case 3 -> postManagement(user);
                default -> {
                    System.out.println("enter 1 to 3");
                    editPost(post, user);
                }
            }
        } catch (Exception ex) {
            System.out.println("invalid input!, going back!");
            editPost(post, user);
        }
    }

    public void removeComment(User user, Post post) {
        for (int commentId = 0; commentId < post.getComments().size(); commentId++) {
            System.out.println(commentId + post.getComments().get(commentId));
        }
        System.out.println("""
                enter the index of the comment you would like to remove or enter 0 to go to post management""");
        choice = scanner.nextLine();
        try {
            int commentIndex = Integer.parseInt(choice);
            post.getComments().remove(commentIndex);
            System.out.println("operation successful!, redirecting to postManagement");
            postManagement(user);
        } catch (Exception e) {
            System.out.println("invalid index, going back!");
            removeComment(user, post);
        }
    }

    public void profileManagement(User user) {
        System.out.println("""
                welcome to profile management, select your action:\s
                1) view profile
                2) edit profile
                3) go back to dashboard""");
        choice = scanner.nextLine();
        try {
            // try to parse choice, if invalid string is input, go to catch
            switch (Integer.parseInt(choice)) {
                case 1 -> viewProfile(user);
                case 2 -> editProfile(user);
                case 3 -> dashboard(user);
                default -> {
                    System.out.println("enter 1 to 3");
                    profileManagement(user);
                }
            }
        } catch (Exception ex) {
            System.out.println("invalid input!, going back to profile management");
            profileManagement(user);
        }
    }

    public void viewProfile(User user) {
        System.out.println("username : " + user.getUsername());
        System.out.println("name : " + user.getName());
        if (Objects.nonNull(user.getProfile().getBio())) System.out.println("bio : " + user.getProfile().getBio());
        if (Objects.nonNull(user.getProfile().getBirthday()))
            System.out.println("birthday : " + user.getProfile().getBirthday());
        System.out.println("followers count : " + user.getProfile().getFollowers().size());
        System.out.println("following count : " + user.getProfile().getFollowing().size());
        for (int i = 0; i < user.getProfile().getPosts().size(); i++) {
            System.out.println(user.getProfile().getPosts().get(i).getText());
        }
        System.out.println("enter any input to go back to profile management");
        choice = scanner.nextLine();
        profileManagement(user);
    }

    public void editProfile(User user) {
        System.out.println("your current bio is: " + user.getProfile().getBio());
        System.out.println("your current birthday is: " + user.getProfile().getBirthday());
        System.out.println("""
                what would you like to do?
                1) edit bio
                2) edit birthday
                3) go back to profile management""");
        choice = scanner.nextLine();
        try {
            // try to parse choice, if invalid string is input, go to catch
            switch (Integer.parseInt(choice)) {
                case 1 -> {
                    System.out.println("enter your new bio :");
                    choice = scanner.nextLine();
                    user.getProfile().setBio(choice);
                    System.out.println("operation successful!");
                    editProfile(user);
                }
                case 2 -> {
                    System.out.println("enter your birthday :");
                    choice = scanner.nextLine();
                    user.getProfile().setBirthday(choice);
                    System.out.println("operation successful!");
                    editProfile(user);
                }
                case 3 -> profileManagement(user);
                default -> {
                    System.out.println("enter 1 to 3");
                    editProfile(user);
                }
            }
        } catch (Exception ex) {
            System.out.println("invalid input!, going back to profile management");
            profileManagement(user);
        }
    }

}
