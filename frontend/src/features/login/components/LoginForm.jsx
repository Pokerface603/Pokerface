import Input from "@component/Input";
import Parchment from "@component/Parchment";
import Wanted from "@component/Wanted";
import WoodBackground from "@component/WoodBackground";
import cowboy from "../../../assets/images/cowboy.svg";
import TextButton from "@component/TextButton";

const LoginForm = () => {
  return (
    <>
      <WoodBackground>
        <Parchment style={{ height: "900px", width: "680px" }}>
          <div className="h-1/4 flex item-center">
            <Wanted width="548px" height="171px" text="DEATH OR ALIVE" />
          </div>
          <div className="h-2/5 flex justify-center">
            <img src={cowboy} alt="cowboy" />
          </div>
          <div className="h-1/3">
            <div className="flex flex-col items-center justify-center">
              <Input width={"282px"} height={"59px"} placeholder={"ID"} type="email" />
              <Input width={"282px"} height={"59px"} placeholder={"PASSWORD"} type="password" />
              <div className="flex mt-3 gap-7">
                <TextButton
                  width="82px"
                  height="28px"
                  fontSize="28px"
                  text="로그인"
                /> 
                <p style={{  fontSize: "25px"}}>|</p>
                <TextButton
                  width="99px"
                  height="25px"
                  fontSize="28px"
                  text="회원가입"
                />
              </div>
              <div className="mt-3">
              <TextButton
                width="99px"
                height="25px"
                fontSize="25px"
                text="KAKAO"
              />
              </div>
            </div>
          </div>
        </Parchment>
      </WoodBackground>
    </>
  );
};

export default LoginForm;
