// -*- Java -*-
/*!
 * @file VirtualJoystick.java
 * @date $Date$
 *
 * $Id$
 */

import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.RTObject_impl;
import jp.go.aist.rtm.RTC.RtcDeleteFunc;
import jp.go.aist.rtm.RTC.RtcNewFunc;
import jp.go.aist.rtm.RTC.RegisterModuleFunc;
import jp.go.aist.rtm.RTC.util.Properties;

/*!
 * @class VirtualJoystick
 * @brief Virtual Joystick
 */
public class VirtualJoystick implements RtcNewFunc, RtcDeleteFunc, RegisterModuleFunc {

//  Module specification
//  <rtc-template block="module_spec">
    public static String component_conf[] = {
    	    "implementation_id", "VirtualJoystick",
    	    "type_name",         "VirtualJoystick",
    	    "description",       "Virtual Joystick",
    	    "version",           "1.0.0",
    	    "vendor",            "ysuga_net",
    	    "category",          "Exampl",
    	    "activity_type",     "STATIC",
    	    "max_instance",      "1",
    	    "language",          "Java",
    	    "lang_type",         "compile",
            // Configuration variables
            "conf.default.gain", "1.0",
            // Widget
            "conf.__widget__.gain", "text",
            // Constraints
    	    ""
            };
//  </rtc-template>

    public RTObject_impl createRtc(Manager mgr) {
        return new VirtualJoystickImpl(mgr);
    }

    public void deleteRtc(RTObject_impl rtcBase) {
        rtcBase = null;
    }
    public void registerModule() {
        Properties prop = new Properties(component_conf);
        final Manager manager = Manager.instance();
        manager.registerFactory(prop, new VirtualJoystick(), new VirtualJoystick());
    }
}
