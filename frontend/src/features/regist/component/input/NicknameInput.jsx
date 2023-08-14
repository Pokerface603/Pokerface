import React from "react";
import Input from "@component/Input";
import CheckIcon from "../registform/CheckIcon";
import {
  height,
  nameWidth,
  checkWidth,
  nicknameStyles,
} from "../registform/registFormStyle";

const NicknameInput = ({ onChange, isNickName, nickNameMessage }) => {
  return (
    <>
      <div className="flex">
        <Input
          width={nameWidth}
          height={height}
          type={nicknameStyles.type}
          placeholder={nicknameStyles.placeholder}
          onChange={onChange}
        />
        <CheckIcon
          width={checkWidth}
          height={height}
          filter={`brightness(${isNickName ? 0.75 : 0})`}
        />
      </div>
      {nickNameMessage && (
        <span
          className="justify-self-start"
          style={{
            fontFamily: "NexonGothic",
            marginLeft: "205px",
            color: `${isNickName ? "green" : "red"}`,
          }}
        >
          {nickNameMessage}
        </span>
      )}
    </>
  );
};

export default NicknameInput;
