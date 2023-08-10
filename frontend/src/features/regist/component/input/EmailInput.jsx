import React from "react";
import Input from "@component/Input";
import CheckIcon from "../registform/CheckIcon";
import {
  height,
  nameWidth,
  checkWidth,
  idStyles,
} from "../registform/registFormStyle";

const EmailInput = ({ onChange, isEmail, emailMessage }) => {
  return (
    <>
      <div className="flex" style={{ alignItems: "center" }}>
        <Input
          width={nameWidth}
          height={height}
          type={idStyles.type}
          placeholder={idStyles.placeholder}
          onChange={onChange}
        />
        <CheckIcon
          width={checkWidth}
          height={height}
          filter={`brightness(${isEmail ? 0.75 : 0})`}
        />
      </div>
      {emailMessage && (
        <span
          className="justify-self-start"
          style={{
            fontFamily: "NexonGothic",
            marginLeft: "205px",
            color: `${isEmail ? "green" : "red"}`,
          }}
        >
          {emailMessage}
        </span>
      )}
    </>
  );
};

export default EmailInput;
