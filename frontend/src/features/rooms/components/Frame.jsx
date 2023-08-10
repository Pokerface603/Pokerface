import React from "react";
import FrameImage from "../../../assets/images/rooms/frame.svg";

const Logo = ({style}) => {
    return(
       <img src={FrameImage} alt="frame" style={{ ...style}} />
    )
}

export default Logo;