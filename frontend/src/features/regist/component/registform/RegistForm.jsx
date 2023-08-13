import React, { useState } from "react";
import EmailInput from "../input/EmailInput";
import NicknameInput from "../input/NicknameInput";
import PasswordInputs from "../input/PasswordInputs";
import RegistStamp from "../RegistStamp";
import { useNavigate } from "react-router-dom";
import {
  checkEmailAvailability,
  checkNicknameAvailability,
  regist,
} from "../../api/api";

const RegistForm = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [nickName, setNickName] = useState("");
  const [password, setPassword] = useState("");
  const [emailMessage, setEmailMessage] = useState("");
  const [nickNameMessage, setNickNameMessage] = useState("");
  const [passwordMessage, setPasswordMessage] = useState("");
  const [isEmail, setIsEmail] = useState(false);
  const [isPassword, setIsPassword] = useState(false);
  const [passwordConfirmation, setPasswordConfirmation] = useState("");
  const [isNickName, setIsNickName] = useState(false);
  const [capsLockFlag, setCapsLockFlag] = useState(false);
  const [capsLockMessage, setCapsLockMessage] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const checkCapsLock = (e) => {
    let capsLock = e.getModifierState("CapsLock");
    setCapsLockFlag(capsLock);
    setCapsLockMessage("caps lock이 켜져있습니다.");
  };

  const onChangeEmail = async (e) => {
    const emailRegex =
      /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.[A-Za-z]{2,3}$/;
    const emailCurrent = e.target.value;
    setEmail(emailCurrent);
    if (!emailRegex.test(emailCurrent)) {
      setEmailMessage("이메일 형식을 확인해주세요.");
      setIsEmail(false);
      return;
    } else {
      try {
        const isAvailable = await checkEmailAvailability(emailCurrent);
        if (isAvailable) {
          setEmailMessage("사용 가능한 아이디 입니다.");
          setIsEmail(true);
        } else {
          setEmailMessage("이미 사용중인 아이디 입니다.");
          setIsEmail(false);
        }
      } catch (error) {
        // console.error(error);
      }
    }
  };

  const onChangeNickName = async (e) => {
    const nicknameRegex = /^[a-zA-Z0-9ㄱ-ㅎ가-힣]{1,5}$/;
    let nickname = e.target.value;
    setNickName(e.target.value);
    if (!nicknameRegex.test(nickname)) {
      setNickNameMessage("특수문자를 제거한 5자 이하의 닉네임만 가능합니다.");
      setIsNickName(false);
    } else {
      try {
        const isAvailable = await checkNicknameAvailability(nickname);
        if (isAvailable) {
          setNickNameMessage("사용 가능한 닉네임 입니다.");
          setIsNickName(true);
        } else {
          setNickNameMessage("이미 사용중인 닉네임 입니다.");
          setIsNickName(false);
        }
      } catch (error) {
        // console.error(error);
      }
    }
  };

  const onChangePassword = (e) => {
    const newPassword = e.target.value;
    setPassword(newPassword);
    if (passwordCheck(newPassword, passwordConfirmation)) {
      setPasswordMessage("비밀번호가 동일합니다");
      setIsPassword(true);
    } else {
      setPasswordMessage("비밀번호가 다릅니다.");
      setIsPassword(false);
    }
  };

  const onChangePasswordChk = (e) => {
    const newPasswordConfirmation = e.target.value;
    setPasswordConfirmation(newPasswordConfirmation);
    if (passwordCheck(password, newPasswordConfirmation)) {
      setPasswordMessage("비밀번호가 동일합니다");
      setIsPassword(true);
    } else {
      setPasswordMessage("비밀번호가 다릅니다.");
      setIsPassword(false);
    }
  };

  const passwordCheck = (password, confirmation) => {
    return password === confirmation;
  };

  const clickRegistStamp = async () => {
    if (!isEmail) {
      return alert("이메일 입력을 확인해주세요.");
    } else if (!isNickName) {
      return alert("닉네임 입력을 확인해주세요.");
    } else if (!isPassword) {
      return alert("비밀번호 입력을 확인해주세요.");
    } else {
      try {
        setIsLoading(true);
        const response = await regist(email, nickName, password);
        if (response.status === 200) {
          alert("회원가입이 완료되었습니다. \n이메일 인증을 완료해주세요!");
          // 회원가입 완료시 로그인 페이지로 이동.
          navigate("/");
        }
      } catch (error) {
        // console.error(error);
      }
    }
  };

  return (
    <>
      <form>
        <div
          className="grid grid-cols-1 justify-items-center content-center"
          style={{ width: "912px", height: "320px" }}
        >
          <EmailInput
            onChange={onChangeEmail}
            isEmail={isEmail}
            emailMessage={emailMessage}
          />

          <NicknameInput
            onChange={onChangeNickName}
            isNickName={isNickName}
            nickNameMessage={nickNameMessage}
          />

          <PasswordInputs
            onChangePassword={onChangePassword}
            onKeyDown={checkCapsLock}
            isPassword={isPassword}
            passwordMessage={passwordMessage}
            capsLockFlag={capsLockFlag}
            capsLockMessage={capsLockMessage}
            onChangePasswordChk={onChangePasswordChk}
            password={password}
          />
        </div>
      </form>
      {!isLoading && <RegistStamp onClick={clickRegistStamp} />}
    </>
  );
};

export default RegistForm;
