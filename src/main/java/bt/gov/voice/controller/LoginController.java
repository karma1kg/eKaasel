package bt.gov.voice.controller;

import bt.gov.g2c.framework.common.vo.Role;
import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import bt.gov.g2c.framework.userdetail.InvokeWS;
import bt.gov.voice.dto.UserLoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wso2.client.model.G2C_CommonBusinessAPI.UserRoleObj;
import org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by USER on 6/23/2021.
 */

/**
 * for cas login
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

  /**/
    /*@RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }*/
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
       /* return "logout";*/
        return "redirect:https://www.citizenservices.gov.bt/cas/logout";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String LoginRedirectController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Role currentRole = null;
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        UserRolePrivilege dto = new UserRolePrivilege();
        String uid = "";
        HttpSession session = request.getSession();
        String roleId = request.getParameter("roleId");
        try {
            if (request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
                System.out.println("when getting user principal: " + request.getUserPrincipal());
                uid = request.getUserPrincipal().getName();
            } else if (request.getParameter("username") != null) {
                uid = (String) request.getParameter("username");
            }
            if (uid != null) {
                dto.setUserId(uid);
                ResourceBundle resourceBundle= ResourceBundle.getBundle("wsEndPointURL_en_US");
                String userRolePrivilegeEndPointUrl = resourceBundle.getString("getUserRolePrivilege.endPointURL");
                InvokeWS invokeWS = null;
                invokeWS = new InvokeWS(userRolePrivilegeEndPointUrl);
                dto = invokeWS.populateUserRolePrivilegeHierarchy(dto, roleId, "M_MOCS.D_CS");
                UserRolePrivilegeHierarchyObj userRolePrivilegeObj = new UserRolePrivilegeHierarchyObj();
                  /*  Jurisdiction[] jurisdictions = dto.getJurisdictions();*/

                //set user person details

                userRolePrivilegeObj.setFullName(dto.getUserName());
                userRolePrivilegeObj.setUserType(dto.getUserType());
                userRolePrivilegeObj.setEmail(dto.getEmail());
                userRolePrivilegeObj.setCid(dto.getUserCID());
                userRolePrivilegeObj.setMobile(dto.getMobileNumber());

                //set role model datas
                UserRoleObj userRoleObj = new UserRoleObj();
                userRoleObj.setRoleId(Integer.parseInt(dto.getCurrentRole().getRoleId()));
                userRoleObj.setRoleName(dto.getCurrentRole().getRoleName());


//set services and privileges of current role
             /*   int serviceLength = dto.getCurrentRole().getServices().length;
                DeptServicesObj deptServicesObj = new DeptServicesObj();
                List<DeptServiceObj> deptServiceList = new ArrayList<DeptServiceObj>();
                for (int i = 0; i < serviceLength; i++) {
                    DeptServiceObj deptServiceObj = new DeptServiceObj();
                    deptServiceObj.setServiceId(Integer.parseInt(dto.getCurrentRole().getServices(i).getServiceId()));
                    deptServiceObj.setServiceName(dto.getCurrentRole().getServices(i).getServiceName());
                    deptServiceList.add(deptServiceObj);
                    deptServicesObj.setDeptService(deptServiceList);
                    int privilegeLength = dto.getCurrentRole().getServices(i).getPrivileges().length;
                    ServicePrivilegesObj servicePrivilegesObj = new ServicePrivilegesObj();
                    List<ServicePrivilegeObj> servicePrivilegeObjList = new ArrayList<ServicePrivilegeObj>();
                    for (int j = 0; j < privilegeLength; j++) {
                        ServicePrivilegeObj servicePrivilegeObj = new ServicePrivilegeObj();
                        servicePrivilegeObj.setPrivilegeId(Integer.parseInt(dto.getCurrentRole().getServices(i).getPrivileges(j).getPrivilegeId()));
                        servicePrivilegeObj.setPrivilegeName(dto.getCurrentRole().getServices(i).getPrivileges(j).getPrivilegeName());
                        servicePrivilegeObjList.add(servicePrivilegeObj);
                        servicePrivilegesObj.setServicePrivilege(servicePrivilegeObjList);
                        deptServiceObj.setServicePrivileges(servicePrivilegesObj);
                    }
                    userRoleObj.setDeptServices(deptServicesObj);
                }
*/
                // set role object to list
              /*  List<UserRoleObj> userRoleList = new ArrayList<UserRoleObj>();
                userRoleList.add(userRoleObj);

                // set role list to roles object
                UserRolesObj userRolesObj = new UserRolesObj();
                userRolesObj.setUserRole(userRoleList);
                userRolePrivilegeObj.setUserRoles(userRolesObj);


                userLoginDTO.setActorId(uid);
                userLoginDTO.setFullName(uid);
                userLoginDTO.setAssignedUserId(uid);
                userLoginDTO.setCid(userRolePrivilegeObj.getCid());
                userLoginDTO.setMobileNo(userRolePrivilegeObj.getMobile());
                userLoginDTO.setEmailId(userRolePrivilegeObj.getEmail());
                userLoginDTO.setUserType(userRolePrivilegeObj.getUserType());
                userLoginDTO.setTelephoneNo(userRolePrivilegeObj.getTelephone());

                List<Role> roles = new ArrayList<Role>();
                UserRolesObj userRoleList1 = userRolePrivilegeObj.getUserRoles();
                for (int i = 0; i < userRoleList1.getUserRole().size(); i++) {
                    UserRoleObj userRole = userRoleList1.getUserRole().get(i);
                    Role role = new Role();
                    userLoginDTO.setRoleId(userRole.getRoleId());
                    userLoginDTO.setRoleName(userRole.getRoleName());

                   *//* List<Service> services = new ArrayList<Service>();
                    DeptServicesObj deptServicesObj1 = userRole.getDeptServices();
                    for (int j = 0; j < deptServicesObj1.getDeptService().size(); j++) {
                        DeptServiceObj deptServiceObj = deptServicesObj1.getDeptService().get(j);

                        Service service = new Service();
                        List<Privilege> privileges = new ArrayList<Privilege>();
                        userLoginDTO.setServiceId(deptServiceObj.getServiceId());
                        service.setServiceCode(deptServiceObj.getServiceDescription());
                        service.setServiceName(deptServiceObj.getServiceName());
                        ServicePrivilegesObj servicePrivilegesObj = deptServiceObj.getServicePrivileges();
                        for (int k = 0; k < servicePrivilegesObj.getServicePrivilege().size(); k++) {
                            ServicePrivilegeObj servicePrivilegeObj = servicePrivilegesObj.getServicePrivilege().get(k);

                            Privilege privilege = new Privilege();

                            userLoginDTO.setAssignedPrivId(servicePrivilegeObj.getPrivilegeName());
                            userLoginDTO.setAssignedGroupId(servicePrivilegeObj.getPrivilegeName());
                            privilege.setPrivilegeCode(servicePrivilegeObj.getPrivilegeDescription());
                            privilege.setPrivilegeName(servicePrivilegeObj.getPrivilegeName());
                            privileges.add(privilege);
                        }

                        service.setPrivileges(privileges.toArray(new Privilege[privileges.size()]));
                        services.add(service);
                    }
                    role.setServices(services.toArray(new Service[services.size()]));*//*
                    roles.add(role);
                }

                dto.setRoles(roles.toArray(new Role[roles.size()]));*/
            }
            session.setAttribute("UserLoginDTO", dto);

            if (dto.getCurrentRole().getRoleId().equalsIgnoreCase("4001")) { // PmoLogin
                return "redirect:/pmoLogin";
            }else if (dto.getCurrentRole().getRoleId().equalsIgnoreCase("4012")) { // MOE
                return "redirect:/ministryLogin";
            }
            else if (dto.getCurrentRole().getRoleId().equalsIgnoreCase("4003")) { // PMLogin
                return "redirect:/pmLogin";}
            else if (dto.getCurrentRole().getRoleId().equalsIgnoreCase("4062")) { // President
                return "redirect:/dept_login";
            } else if (dto.getCurrentRole().getRoleId().equalsIgnoreCase("9416")) { // Vice President
                return "redirect:/chiefAoTaskList";
            } else if (dto.getCurrentRole().getRoleId().equalsIgnoreCase("9417")) { // Student
                return "redirect:/taskList";
            } else if (dto.getCurrentRole().getRoleId().equalsIgnoreCase("9418")) { // Scholarship Application Verifier
                return "redirect:/taskList";
            } else if (dto.getCurrentRole().getRoleId().equalsIgnoreCase("9419")) { // BSA - Dealing Officer
                return "redirect:/bsaDoTaskList";
            } else if (dto.getCurrentRole().getRoleId().equalsIgnoreCase("9420")) { // Third Country - Dealing Officer
                return "redirect:/taskList";
            } else if (dto.getCurrentRole().getRoleId().equalsIgnoreCase("9421")) { // GOI - Dealing Officer
                return "redirect:/taskList";
            } else if (dto.getCurrentRole().getRoleId().equalsIgnoreCase("9422")) { // Tertiary - Dealing Officer
                return "redirect:/tertiaryDoTaskList";
            } else if (dto.getCurrentRole().getRoleId().equalsIgnoreCase("9423")) { // Chief AO
                return "redirect:/taskList";
            }else {
                return "403";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
