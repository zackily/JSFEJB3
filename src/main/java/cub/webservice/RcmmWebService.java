package cub.webservice;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import cub.entities.OrderInfo;
import cub.entities.RuleList;
import cub.facade.OrderInfoFacade;
import cub.facade.RuleListFacade;
import cub.log.LogAspect;
import cub.vo.RcmmRequestObject;
import cub.vo.RcmmResponseObject;

@Stateless
@LocalBean
@Path("/rcmmService")
@Api(value = "/rcmmService", description = "Welcome to Rcmm Service!")
@Interceptors(LogAspect.class)
public class RcmmWebService {

    @EJB
    private OrderInfoFacade orderInfoFacade;
    @EJB
    private RuleListFacade ruleListFacade;

    @POST
    @Produces("application/json")
    public RcmmResponseObject getResponsePost(RcmmRequestObject request) {
        Short channelCode = orderInfoFacade.getChannelCode(request.getAPID());
        String orderNo = insertOrderInfo(request, channelCode);
        List<RuleList> ruleList = ruleListFacade.joinRuleList(request, channelCode);
        System.out.println(ruleList.size());
        RcmmResponseObject rcmmResponseObject = new RcmmResponseObject();
        rcmmResponseObject.setReturnMessage("Success");
        rcmmResponseObject.setReturnCode("0000");
        rcmmResponseObject.setOrderNumber(orderNo);
        rcmmResponseObject.setUpdateTime(LocalDateTime.now());
        return rcmmResponseObject;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Say Hello World", notes = "Anything Else?")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Something wrong in Server") })
    public Response sayHello() {
        JsonObject value = Json.createObjectBuilder()
            .add("firstName", "Jeremy")
            .add("lastName", "Chung")
            .add("message", "Hello World!")
            .build();
        return Response.status(200).entity(value).build();
    }

    private String insertOrderInfo(RcmmRequestObject request, Short channelCode) {
        OrderInfo orderInfo = new OrderInfo();
        String orderInfoSeq = orderInfoFacade.getOrderInfoSeq();
        Calendar cal = Calendar.getInstance();
        cal.setTime(request.getOrderDttm());
        String orderNo = "TR" + cal.get(Calendar.YEAR) + orderInfoSeq.substring(1, orderInfoSeq.length());
        orderInfo.setOrderNo(orderNo);
        orderInfo.setChannelCode(channelCode);
        orderInfo.setCheckTiming(request.getCheckTiming());
        orderInfo.setClientId(request.getClientId());
        orderInfo.setClientIdSwitchIn(request.getClientIdSwitchIn());
        orderInfo.setOrderAmt(request.getOrderAmt());
        orderInfo.setOrderCur(request.getOrderCur());
        orderInfo.setOrderDttm(request.getOrderDttm());
        orderInfo.setOrderPrice(request.getOrderPrice());
        orderInfo.setOrderQty(request.getOrderQty());
        orderInfo.setPrdCode(request.getPrdCode());
        orderInfo.setSecCode(request.getSecCode());
        orderInfo.setSecCodeSwitchIn(request.getSecCodeSwitchIn());
        orderInfo.setTradeType(request.getTradeType());
        orderInfoFacade.create(orderInfo);
        return orderNo;
    }
}
