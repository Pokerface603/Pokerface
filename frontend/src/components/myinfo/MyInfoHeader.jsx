import React from "react";
const MyInfoHeader = ({ nickname, button }) => {
  let fontSize = "30px"; // 기본 폰트 사이즈
  if (nickname && nickname.length > 5 && nickname.length < 9) {
    fontSize = "22px"; // 길이가 5보다 크고 9보다 작을 때 폰트 사이즈를 20으로 변경
  }

  return (
    <div className="flex mt-2 justify-between">
      {nickname && (
        <span
          className="mx-3"
          style={{
            fontFamily: "NexonGothic",
            fontSize: fontSize, // 동적으로 설정된 폰트 사이즈 적용
            marginBottom: "-1rem",
          }}
        >
          {nickname}
        </span>
      )}
      {button}
    </div>
  );
};

export default MyInfoHeader;
