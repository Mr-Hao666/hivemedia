package cn.hivemedia.controller;

import cn.hivemedia.entity.SysOssEntity;
import cn.hivemedia.entity.model.PlatformAddress;
import cn.hivemedia.msg.ICaptchaApi;
import cn.hivemedia.oss.OSSFactory;
import cn.hivemedia.result.BaseResult;
import cn.hivemedia.service.SysConfigService;
import cn.hivemedia.service.SysOssService;
import cn.hivemedia.service.UserService;
import cn.hivemedia.utils.KuaiDi100Util;
import cn.hivemedia.common.exception.RRException;
import cn.hivemedia.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author 杨浩
 * @create 2018-12-30 14:57
 **/
@RestController
@RequestMapping("/api/publicInformation")
@Api(tags = "公用信息")
public class ApiPublicInformationController {

    @Autowired
    private ICaptchaApi iCaptchaApi;

    @Autowired
    private UserService userService;
    @Autowired
    private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    @ApiOperation("文件上传")
    @PostMapping("/uploadFiles")
    public BaseResult<SysOssEntity> uploadFiles(
            @ApiParam("文件") @RequestParam(value = "file", required = false) MultipartFile file,
            @ApiParam("文件地址") @RequestParam(value = "url", required = false) String url) throws Exception {
        Optional<MultipartFile> fileOptional = Optional.ofNullable(file);
        if (!fileOptional.isPresent() && "".equals(url)) {
            throw new RRException("上传文件不能为空");
        }

        //上传文件
        if (fileOptional.isPresent()) {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            url = OSSFactory.build().uploadSuffix(file.getBytes(), suffix);
        }

        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        sysOssService.insert(ossEntity);

        return BaseResult.success(ossEntity);
    }
    @ApiOperation("调用实名认证接口")
    @GetMapping("/authName")
    public BaseResult authName(@ApiParam("证件号码") @RequestParam("idCardNo") String idCardNo
            ,@ApiParam("用户Id") @RequestParam("userId") Long userId
            ,@ApiParam("姓名") @RequestParam("realName") String realName) {
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccessful(userService.verify(userId, realName, idCardNo));
        return baseResult;
    }

    @ApiOperation("查询平台收货地址")
    @GetMapping("/platformAddress")
    public BaseResult platformAddress(){
        PlatformAddress platformAddress = new PlatformAddress();
        platformAddress.setAddress(sysConfigService.getValue("platform_address"));
        platformAddress.setName(sysConfigService.getValue("platform_name"));
        platformAddress.setPhone(sysConfigService.getValue("platform_phone"));
        return BaseResult.success(platformAddress);
    }

    @ApiOperation("查询快递信息")
    @GetMapping("/postMessage")
    public BaseResult postMessage(@ApiParam("物流编号") @RequestParam("express_no") String expressNo){
        String emsCode = "shunfeng";
        String param = "{\"com\":\"" + emsCode + "\",\"num\":\"" + expressNo + "\"}";
        List<Map<Object, Object>> result = KuaiDi100Util.getInstance().getKuaiDiInfo4Firm(param);
        return BaseResult.success(result);
    }
}