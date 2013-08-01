// -*- Java -*-
/*!
 * @file  VirtualJoystickImpl.java
 * @brief Virtual Joystick
 * @date  $Date$
 *
 * $Id$
 */

import javax.swing.SwingUtilities;

import jp.go.aist.rtm.RTC.DataFlowComponentBase;
import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.port.OutPort;
import jp.go.aist.rtm.RTC.util.DataRef;
import jp.go.aist.rtm.RTC.util.LongHolder;
import RTC.ReturnCode_t;
import RTC.TimedLongSeq;

/*!
 * @class VirtualJoystickImpl
 * @brief Virtual Joystick
 *
 */
public class VirtualJoystickImpl extends DataFlowComponentBase {

	private JoyFrame frame;
  /*!
   * @brief constructor
   * @param manager Maneger Object
   */
	public VirtualJoystickImpl(Manager manager) {  
        super(manager);
        // <rtc-template block="initializer">
        m_out_val = new TimedLongSeq(new RTC.Time(0,0), null);
        m_out = new DataRef<TimedLongSeq>(m_out_val);
        m_outOut = new OutPort<TimedLongSeq>("out", m_out);
        // </rtc-template>

    }

    /*!
     *
     * The initialize action (on CREATED->ALIVE transition)
     * formaer rtc_init_entry() 
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onInitialize() {
        // Registration: InPort/OutPort/Service
        // <rtc-template block="registration">
        
        // Set OutPort buffer
        addOutPort("out", m_outOut);
        // </rtc-template>
        bindParameter("gain", m_gain, "1000");
        
        initUI();
        return super.onInitialize();
    }
    
    
    protected void initUI() {
    	//SwingUtilities.invokeLater(new Runnable() {
         //   public void run() {
	//System.out.println("[RTC.VirtualJoystick] - onInitialize (Ver. 1.0.0)");
	frame = new JoyFrame();
	//frame.setLocation(740, 0);
	//frame.setSize(300, 300);
	//frame.pack();
	//frame.setVisible(true);
	//   }
        //});
    }
    

    /***
     *
     * The finalize action (on ALIVE->END transition)
     * formaer rtc_exiting_entry()
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onFinalize() {
//        return super.onFinalize();
//    }

    /***
     *
     * The startup action when ExecutionContext startup
     * former rtc_starting_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onStartup(int ec_id) {
//        return super.onStartup(ec_id);
//    }

    /***
     *
     * The shutdown action when ExecutionContext stop
     * former rtc_stopping_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onShutdown(int ec_id) {
//        return super.onShutdown(ec_id);
//    }

    /***
     *
     * The activated action (Active state entry action)
     * former rtc_active_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onActivated(int ec_id) {
    	//initUI();
    	frame.setState(JoyFrame.DEF);
        return super.onActivated(ec_id);
    }

    /***
     *
     * The deactivated action (Active state exit action)
     * former rtc_active_exit()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onDeactivated(int ec_id) {
        return super.onDeactivated(ec_id);
    }

    /***
     *
     * The execution action that is invoked periodically
     * former rtc_active_do()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onExecute(int ec_id) {
    	int state = frame.getState();
    	m_out.v.data = new int[2];
    	switch(state) {
    	case JoyFrame.UP:
    		m_out.v.data[0] = 0;
    		m_out.v.data[1] = m_gain.value.intValue();
    		break;
    	case JoyFrame.RIGHT:
	    m_out.v.data[0] = m_gain.value.intValue();
    		m_out.v.data[1] = 0;
    		break;
    	case JoyFrame.LEFT:
	    m_out.v.data[0] = -m_gain.value.intValue();
    		m_out.v.data[1] = 0;
    		break;
    	case JoyFrame.DOWN:
       		m_out.v.data[0] = 0;
    		m_out.v.data[1] = -m_gain.value.intValue();
    		break;
    	case JoyFrame.DEF:
       		m_out.v.data[0] = 0;
    		m_out.v.data[1] = 0;
    		break;
    	default:
    		break;
    		
    	}
    	m_outOut.write();
        return super.onExecute(ec_id);
    }

    /***
     *
     * The aborting action when main logic error occurred.
     * former rtc_aborting_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//  @Override
//  public ReturnCode_t onAborting(int ec_id) {
//      return super.onAborting(ec_id);
//  }

    /***
     *
     * The error action in ERROR state
     * former rtc_error_do()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    public ReturnCode_t onError(int ec_id) {
//        return super.onError(ec_id);
//    }

    /***
     *
     * The reset action that is invoked resetting
     * This is same but different the former rtc_init_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onReset(int ec_id) {
        return super.onReset(ec_id);
    }

    /***
     *
     * The state update action that is invoked after onExecute() action
     * no corresponding operation exists in OpenRTm-aist-0.2.0
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onStateUpdate(int ec_id) {
//        return super.onStateUpdate(ec_id);
//    }

    /***
     *
     * The action that is invoked when execution context's rate is changed
     * no corresponding operation exists in OpenRTm-aist-0.2.0
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onRateChanged(int ec_id) {
//        return super.onRateChanged(ec_id);
//    }
//
	// Configuration variable declaration
	// <rtc-template block="config_declare">
    /*!
     * 
     * - Name:  gain
     * - DefaultValue: 1000
     */
    protected LongHolder m_gain = new LongHolder();
	// </rtc-template>

    // DataInPort declaration
    // <rtc-template block="inport_declare">
    
    // </rtc-template>

    // DataOutPort declaration
    // <rtc-template block="outport_declare">
    protected TimedLongSeq m_out_val;
    protected DataRef<TimedLongSeq> m_out;
    /*!
     */
    protected OutPort<TimedLongSeq> m_outOut;

    
    // </rtc-template>

    // CORBA Port declaration
    // <rtc-template block="corbaport_declare">
    
    // </rtc-template>

    // Service declaration
    // <rtc-template block="service_declare">
    
    // </rtc-template>

    // Consumer declaration
    // <rtc-template block="consumer_declare">
    
    // </rtc-template>


}
