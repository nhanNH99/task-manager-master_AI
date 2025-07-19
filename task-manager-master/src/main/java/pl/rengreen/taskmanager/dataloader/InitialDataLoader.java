package pl.rengreen.taskmanager.dataloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.rengreen.taskmanager.model.Role;
import pl.rengreen.taskmanager.model.User;
import pl.rengreen.taskmanager.model.Task;
import pl.rengreen.taskmanager.service.RoleService;
import pl.rengreen.taskmanager.service.TaskService;
import pl.rengreen.taskmanager.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserService userService;
    private TaskService taskService;
    private RoleService roleService;
    private final Logger logger = LoggerFactory.getLogger(InitialDataLoader.class);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private pl.rengreen.taskmanager.service.NotificationService notificationService;

    @Value("${default.admin.mail}")
    private String defaultAdminMail;
    @Value("${default.admin.name}")
    private String defaultAdminName;
    @Value("${default.admin.password}")
    private String defaultAdminPassword;
    @Value("${default.admin.image}")
    private String defaultAdminImage;

    public InitialDataLoader(UserService userService, TaskService taskService, RoleService roleService, pl.rengreen.taskmanager.service.NotificationService notificationService) {
        this.userService = userService;
        this.taskService = taskService;
        this.roleService = roleService;
        this.notificationService = notificationService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //ROLES --------------------------------------------------------------------------------------------------------
        roleService.createRole(new Role("ADMIN"));
        roleService.createRole(new Role("USER"));
        roleService.findAll().stream().map(role -> "saved role: " + role.getRole()).forEach(logger::info);

        //USERS --------------------------------------------------------------------------------------------------------
        //1
        User admin = new User(
                defaultAdminMail,
                defaultAdminName,
                defaultAdminPassword,
                defaultAdminImage);
        userService.createUser(admin);
        userService.changeRoleToAdmin(admin);

        //2
        User manager = new User(
                "manager@mail.com",
                "Manager",
                "112233",
                "images/admin.png");
        userService.createUser(manager);
        userService.changeRoleToAdmin(manager);

        //3
        userService.createUser(new User(
                "mark@mail.com",
                "Mark",
                "112233",
                "images/mark.jpg"));

        //4
        userService.createUser(new User(
                "ann@mail.com",
                "Ann",
                "112233",
                "images/ann.jpg"));

        //5
        userService.createUser(new User(
                "ralf@mail.com",
                "Ralf",
                "112233",
                "images/ralf.jpg"));

        //6
        userService.createUser(new User(
                "kate@mail.com",
                "Kate",
                "112233",
                "images/kate.jpg"));

        // Thêm các task demo cho manager làm owner
        LocalDate today = LocalDate.now();
        taskService.createTask(new Task(
                "Prepare monthly report",
                "Manager needs to prepare and submit the monthly report to the board.",
                today.minusDays(3),
                false,
                "Admin",
                userService.getUserByEmail("manager@mail.com")
        ));

        taskService.createTask(new Task(
                "Review team performance",
                "Manager reviews the performance of all team members for Q2.",
                today.minusDays(7),
                false,
                "Admin",
                userService.getUserByEmail("manager@mail.com")
        ));

        taskService.createTask(new Task(
                "Approve budget proposal",
                "Manager approves the new budget proposal for next quarter.",
                today.minusDays(1),
                false,
                "Admin",
                userService.getUserByEmail("manager@mail.com")
        ));

        taskService.createTask(new Task(
                "Organize team meeting",
                "Manager organizes a team meeting to discuss project progress.",
                today.plusDays(2),
                false,
                "Admin",
                userService.getUserByEmail("manager@mail.com")
        ));

        taskService.createTask(new Task(
                "Update company policies",
                "Manager updates the company policies for the new fiscal year.",
                today.plusDays(5),
                false,
                "Admin",
                userService.getUserByEmail("manager@mail.com")
        ));

        //7
        userService.createUser(new User(
                "tom@mail.com",
                "Tom",
                "112233",
                "images/tom.jpg"));

        userService.findAll().stream()
                .map(u -> "saved user: " + u.getName())
                .forEach(logger::info);

        // NOTIFICATIONS --------------------------------------------------------------------------------------------------------
        // Add some initial notifications for demo users
        // Demo notifications for various users
        pl.rengreen.taskmanager.dto.NotificationRequest notification1 = new pl.rengreen.taskmanager.dto.NotificationRequest();
        notification1.setMessage("Welcome to Task Manager, Mark!");
        notification1.setUserId(userService.getUserByEmail("mark@mail.com").getId());
        notificationService.createNotification(notification1);

        pl.rengreen.taskmanager.dto.NotificationRequest notification2 = new pl.rengreen.taskmanager.dto.NotificationRequest();
        notification2.setMessage("Your first task has been assigned.");
        notification2.setUserId(userService.getUserByEmail("ann@mail.com").getId());
        notificationService.createNotification(notification2);

        pl.rengreen.taskmanager.dto.NotificationRequest notification3 = new pl.rengreen.taskmanager.dto.NotificationRequest();
        notification3.setMessage("System maintenance scheduled for tomorrow.");
        notification3.setUserId(null); // broadcast
        notificationService.createNotification(notification3);

        pl.rengreen.taskmanager.dto.NotificationRequest notification4 = new pl.rengreen.taskmanager.dto.NotificationRequest();
        notification4.setMessage("You have 3 tasks due this week.");
        notification4.setUserId(userService.getUserByEmail("ralf@mail.com").getId());
        notificationService.createNotification(notification4);

        pl.rengreen.taskmanager.dto.NotificationRequest notification5 = new pl.rengreen.taskmanager.dto.NotificationRequest();
        notification5.setMessage("Kate, your profile was updated successfully.");
        notification5.setUserId(userService.getUserByEmail("kate@mail.com").getId());
        notificationService.createNotification(notification5);

        pl.rengreen.taskmanager.dto.NotificationRequest notification6 = new pl.rengreen.taskmanager.dto.NotificationRequest();
        notification6.setMessage("Tom, you have been assigned a new project.");
        notification6.setUserId(userService.getUserByEmail("tom@mail.com").getId());
        notificationService.createNotification(notification6);

        pl.rengreen.taskmanager.dto.NotificationRequest notification7 = new pl.rengreen.taskmanager.dto.NotificationRequest();
        notification7.setMessage("Ann, your password was changed.");
        notification7.setUserId(userService.getUserByEmail("ann@mail.com").getId());
        notificationService.createNotification(notification7);

        pl.rengreen.taskmanager.dto.NotificationRequest notification8 = new pl.rengreen.taskmanager.dto.NotificationRequest();
        notification8.setMessage("Manager, you have 2 unread notifications.");
        notification8.setUserId(userService.getUserByEmail("manager@mail.com").getId());
        notificationService.createNotification(notification8);

        pl.rengreen.taskmanager.dto.NotificationRequest notification9 = new pl.rengreen.taskmanager.dto.NotificationRequest();
        notification9.setMessage("Welcome to Task Manager, Ralf!");
        notification9.setUserId(userService.getUserByEmail("ralf@mail.com").getId());
        notificationService.createNotification(notification9);

        pl.rengreen.taskmanager.dto.NotificationRequest notification10 = new pl.rengreen.taskmanager.dto.NotificationRequest();
        notification10.setMessage("System update completed successfully.");
        notification10.setUserId(null); // broadcast
        notificationService.createNotification(notification10);


        //TASKS --------------------------------------------------------------------------------------------------------
        //tasks from Web Design Checklist
        //https://www.beewits.com/the-ultimate-web-design-checklist-things-to-do-when-launching-a-website/



        //1
        taskService.createTask(new Task(
                "Collect briefing document ",
                "Setup first meeting with client. Collect basic data about the client. Define and collect briefing document from client.",
                today.minusDays(40),
                true,
                userService.getUserByEmail("mark@mail.com").getName(),
                userService.getUserByEmail("mark@mail.com")
        ));

        //2
        taskService.createTask(new Task(
                "Define project questionnaire ",
                "Define and send project questionnaire to the client and wait for the client’s response. Iterate on doubts you have until everybody is in agreement. Finalize project questionnaire from client.",
                today.minusDays(30),
                true,
                userService.getUserByEmail("ann@mail.com").getName(),
                userService.getUserByEmail("ann@mail.com")
        ));

        //3
        taskService.createTask(new Task(
                "Research client’s company and industry",
                "Research client’s company to understand their brand, the way they communicate, their demographics, target audience. Research client’s industry to find ways of communicating specifically to the industry, strengths and weaknesses, trends and other industry specifics.",
                today.minusDays(20),
                true,
                userService.getUserByEmail("ralf@mail.com").getName(),
                userService.getUserByEmail("ralf@mail.com")
        ));

        //4
        taskService.createTask(new Task(
                "Get quotation for project",
                "Get quotation for development effort for project. Estimate design work with designers. Get quotation for copy/content or estimate work with your copywriters.  Get quotation for photography and video production or estimate effort involved.",
                today.minusDays(10),
                true,
                userService.getUserByEmail("kate@mail.com").getName(),
                userService.getUserByEmail("kate@mail.com")
        ));

        //5
        taskService.createTask(new Task(
                "Get quotation for hosting and domain",
                "Get quotation for hosting and domain, particularly if specialized hosting is involved such as VPS hosting, cloud hosting, or special hosting or environment requirements.",
                today.minusDays(5),
                false,
                userService.getUserByEmail("manager@mail.com").getName(),
                userService.getUserByEmail("kate@mail.com")
        ));

        //6
        taskService.createTask(new Task(
                "Check the quality of each content element",
                "Quality assure each piece of content you have outsourced or bought – and ask for changes where necessary. Populate the website content with the various content items you have agreed with the client.",
                today.minusDays(2),
                false,
                userService.getUserByEmail("manager@mail.com").getName(),
                userService.getUserByEmail("mark@mail.com")
        ));

        //7
        taskService.createTask(new Task(
                "Define a Contact Us page and social media details",
                "Define a Contact Us page with correct client details and a map. Populate links and icongraphy with links to relevant social media details. Create a link to your website in the footer (make sure it has been agreed with the client to do so).",
                today.minusDays(1),
                false,
                userService.getUserByEmail("manager@mail.com").getName(),
                userService.getUserByEmail("mark@mail.com")
        ));

        //8
        taskService.createTask(new Task(
                "Check all web copywriting",
                "Make sure web copywriting has been proofread and ran through a spelling and grammar checker to check for correctness. Use online tools such as Reverso, or Spellcheckplus.com. Check that generic content, such as lorem ipsum, has been properly removed and replaced.",
                today,
                false,
                userService.getUserByEmail("manager@mail.com").getName(),
                userService.getUserByEmail("ann@mail.com")
        ));

        //9
        taskService.createTask(new Task(
                "Check all images and videos",
                "See that all images are in the correct places, smushed, formatted, width and height specified and working on all devices. Confirm that videos and audio files are in the correct places, formatted and working on all devices.",
                today.plusDays(1),
                false,
                userService.getUserByEmail("manager@mail.com").getName(),
                userService.getUserByEmail("ann@mail.com")
        ));

        //10
        taskService.createTask(new Task(
                "Check all linked content",
                "Test all linked content, such as case studies, ebooks, and whitepapers, and verify that they are correctly linked. Test to see that all internal links across web pages are working properly. Ensure that company logo is linked to the homepage.",
                today.plusDays(2),
                false,
                userService.getUserByEmail("manager@mail.com").getName(),
                userService.getUserByEmail("kate@mail.com")
        ));

        //11
        taskService.createTask(new Task(
                "Check Contact Us and other Forms",
                "Ensure that Contact Us and other forms are submitting data properly. If the form is sent to an email address ensure that email is received on a mailbox that is monitored, or ensure that content is correctly stored in your database. Verify the Thank-you message or page displayed after form is submitted. Check that Auto-responders are working properly and text in emails has been proofed.",
                today.plusDays(3),
                false,
                userService.getUserByEmail("kate@mail.com").getName(),
                userService.getUserByEmail("kate@mail.com")
        ));

        //12
        taskService.createTask(new Task(
                "Check all external links",
                "External links across web pages are working properly, and open in a new tab (Fix any broken links using this tool). Ensure that Social media share icons are working properly – that there is a good image for sharing and that the description for sharing is appropriate. Correct your metadata as necessary to ensure social media sharing is working ok.",
                today.plusDays(4),
                false,
                userService.getUserByEmail("ann@mail.com").getName(),
                userService.getUserByEmail("ann@mail.com")
        ));

        //13
        taskService.createTask(new Task(
                "Check the 404 page and redirects",
                "Try a non-existing address on your page to check the 404 page and 404 redirect pages are in place. Choose www vs no-www and make sure that ONLY one of them is working to ensure you don’t get penalized for duplicate content. After choosing one, make sure one redirects to the other.",
                today.plusDays(5),
                false,
                userService.getUserByEmail("ralf@mail.com").getName(),
                userService.getUserByEmail("ralf@mail.com")
        ));

        //14
        taskService.createTask(new Task(
                "Check if website is mobile-friendly",
                "Make sure that viewport meta tag is used. Check that  website is mobile-friendly with at least a MobileOk score of 75. Check if Google sees your page as Mobile-Friendly. Make sure that correct input types for email, phone and URL input form fields are used to ensure these are rendered correctly on mobile phones.",
                today.plusDays(6),
                false,
                userService.getUserByEmail("manager@mail.com").getName(),
                userService.getUserByEmail("manager@mail.com")
        ));

        //15
        taskService.createTask(new Task(
                "Test website on emulators and real devices",
                "Check how the site looks on emulators such as ipadpeek, screenfly, mobilephonesimulator. Test the site using real devices you have accessible to you or use opendevicelab.com.",
                today.plusDays(8),
                false,
                userService.getUserByEmail("manager@mail.com").getName()
        ));

        //16
        taskService.createTask(new Task(
                "Check page titles, meta descriptions and keywords",
                "Check that all pages have unique page titles (with a recommended length of fewer than 70 characters, including any keywords). Check that all pages have unique meta descriptions (with a recommended length of fewer than 156 characters, including keywords). Verify that pages have your chosen keywords included without any keyword stuffing (do not over-emphasize particular keywords).",
                today.plusDays(10),
                false,
                userService.getUserByEmail("manager@mail.com").getName()
        ));

        //17
        taskService.createTask(new Task(
                "Check page URLs",
                "See that all page URLs consistently reflect site information architecture. If you have had another older website, make sure you have 301 redirects in place for all old URLs (redirecting old pages to new ones).",
                today.plusDays(12),
                false,
                userService.getUserByEmail("manager@mail.com").getName()
        ));

        //18
        taskService.createTask(new Task(
                "Minify and optimize files",
                "Minify javascript and CSS files. Optimize the size of images and replace the existing images with the optimized images. Specify image dimensions for each image. Enable gzip compression on your hosting server.",
                today.plusDays(14),
                false,
                userService.getUserByEmail("manager@mail.com").getName()
        ));

        //19
        taskService.createTask(new Task(
                "Register social media properties",
                "Create cover images for Social Media such as Facebook, Twitter, LinkedIn company page, Pinterest, Instagram or others as necessary. Register all social media properties and get them set up with profile images, cover pages, links back to the website.",
                today.plusDays(16),
                false,
                userService.getUserByEmail("manager@mail.com").getName()
        ));

        //20
        taskService.createTask(new Task(
                "Send the finished site to the client",
                "Send the finished site to the client and get feedback. Fix and change any requests by client. Give access to client to all accounts created on their behalf. Send updates to client and wait for client sign-off.",
                today.plusDays(18),
                false,
                userService.getUserByEmail("manager@mail.com").getName()
        ));

        taskService.findAll().stream().map(t -> "saved task: '" + t.getName()
                + "' for owner: " + getOwnerNameOrNoOwner(t)).forEach(logger::info);
    }

    private String getOwnerNameOrNoOwner(Task task) {
        return task.getOwner() == null ? "no owner" : task.getOwner().getName();
    }
}
