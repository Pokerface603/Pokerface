import React from "react";
import Input from "@component/Input";
import {
  height,
  pwWidth,
  passwordStyles,
  passCheckStyles,
} from "../registform/registFormStyle";

const PasswordInputs = ({
  onChangePassword,
  onKeyDown,
  isPassword,
  passwordMessage,
  capsLockFlag,
  capsLockMessage,
  onChangePasswordChk,
  password,
}) => {
  return (
    <>
      <Input
        width={pwWidth}
        height={height}
        type={passwordStyles.type}
        placeholder={passwordStyles.placeholder}
        onChange={onChangePassword}
        onKeyDown={onKeyDown}
      />
      {capsLockFlag && (
        <span
          className="justify-self-start"
          style={{
            fontFamily: "NexonGothic",
            marginLeft: "205px",
            color: "red",
          }}
        >
          {capsLockMessage}
        </span>
      )}
      <Input
        width={pwWidth}
        height={height}
        type={passCheckStyles.type}
        placeholder={passCheckStyles.placeholder}
        onChange={onChangePasswordChk}
      />
      {password.length > 0 && (
        <span
          className="justify-self-start"
          style={{
            fontFamily: "NexonGothic",
            marginLeft: "205px",
            color: `${isPassword ? "green" : "red"}`,
          }}
        >
          {passwordMessage}
        </span>
      )}
    </>
  );
};

export default PasswordInputs;
