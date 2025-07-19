
package pl.rengreen.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rengreen.taskmanager.dto.NotificationDeleteRequest;
import pl.rengreen.taskmanager.dto.NotificationRequest;
import pl.rengreen.taskmanager.dto.NotificationResponse;
import pl.rengreen.taskmanager.service.NotificationService;

import javax.validation.Valid;
import java.security.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collections;

@Controller
public class NotificationController {

    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/notifications/view")
    public String listNotifications(Model model, Principal principal, @RequestParam(required = false) String status, Pageable pageable) {
        Long userId = getUserIdFromPrincipal(principal);
        Page<NotificationResponse> notifications = notificationService.getNotifications(userId, status, pageable);
        model.addAttribute("notifications", notifications.getContent());
        model.addAttribute("page", notifications);
        return "views/notification_list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/notifications/{id}/view")
    public String showNotificationDetail(@PathVariable Long id, Model model, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        NotificationResponse notification = notificationService.getNotificationDetail(userId, id);
        model.addAttribute("notification", notification);
        return "views/notification_detail";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/notifications/create")
    public String showCreateNotificationForm(Model model) {
        model.addAttribute("notificationRequest", new NotificationRequest());
        return "forms/notification-new";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/notifications/create")
    public String createNotification(@Valid NotificationRequest notificationRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "forms/notification-new";
        }
        notificationService.createNotification(notificationRequest);
        return "redirect:/notifications/view";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/notifications/{id}/read")
    public String markAsRead(@PathVariable Long id, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        notificationService.markAsRead(userId, Collections.singletonList(id));
        return "redirect:/notifications/view";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/notifications/delete")
    public String deleteNotifications(@Valid NotificationDeleteRequest request, Principal principal) {
        Long userId = getUserIdFromPrincipal(principal);
        notificationService.deleteNotifications(userId, request.getNotificationIds());
        return "redirect:/notifications/view";
    }

    private Long getUserIdFromPrincipal(Principal principal) {
        // TODO: Implement user ID extraction from Principal or SecurityContext
        return 1L;
    }
}
