export const validateEmail = (email, setEmailMessage, setIsEmail) => {
  const emailRegex =
    /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.[A-Za-z]{2,3}$/;

  if (!emailRegex.test(email)) {
    setEmailMessage("이메일 형식이 올바르지 않습니다.");
    setIsEmail(false);
  } else {
    setEmailMessage("유효한 이메일 형식입니다.");
    setIsEmail(true);
  }
};
