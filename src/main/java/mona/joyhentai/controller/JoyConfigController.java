package mona.joyhentai.controller;

import mona.joyhentai.Util.JoyConfig;
import mona.joyhentai.component.CommonUtils;
import mona.joyhentai.model.JoyResult;
import mona.joyhentai.service.JoyConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author manonmona
 * @@Date 2021/10/26 15:49
 */
@RestController
public class JoyConfigController {

    @Autowired
    JoyConfigService joyConfigService;

    @PostMapping("/config/save")
    public JoyResult saveJoyConfig(JoyConfig joyConfig){
        boolean b = joyConfigService.saveJoyConfig(joyConfig);
        JoyResult joyResult = new JoyResult();
        joyResult.setCode(0);
        joyResult.setMsg(b==true?"成功":"失败");
        joyResult.setData(joyConfig);
        return joyResult;
    }

    @GetMapping("/config/get")
    public JoyResult getJoyConfig() {
        JoyConfig joyConfig = CommonUtils.getJoyConfig();
        JoyResult joyResult = new JoyResult();
        joyResult.setCode(0);
        joyResult.setMsg("成功");
        joyResult.setData(joyConfig);
        return joyResult;
    }

    @GetMapping("/config/reset")
    public JoyResult resetJoyConfig(){
        boolean b = joyConfigService.resetJoyConfig();
        JoyResult joyResult = new JoyResult();
        joyResult.setCode(0);
        joyResult.setMsg(b==true?"成功":"失败");
        joyResult.setData(CommonUtils.getJoyConfig());
        return joyResult;
    }
}
