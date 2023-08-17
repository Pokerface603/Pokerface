import React, { useState } from "react";
import { useSelector } from "react-redux";
import TextButton from "@component/TextButton";
import soundEffects from "@config/soundEffects";

const EntryModal = () => {
  const nickname = useSelector((state) => state.user.nickname);

  // 모달창 노출 여부 state
  const [modalOpen, setModalOpen] = useState(
    !localStorage.getItem("modalClosed")
  );

  // 모달 닫기
  const closeModal = () => {
    setModalOpen(false);
    localStorage.setItem("modalClosed", "true");
    soundEffects.shot.play();
    soundEffects.bgm.play();
  };

  return (
    <>
      {modalOpen && (
        <div className="fixed inset-0 bg-white bg-opacity-70 flex items-center justify-center z-50">
          <div
            className="bg-entry-parchment bg-cover h-screen w-screen flex justify-center items-center"
            style={{ width: "900px", height: "570px" }}
          >
            <div
              className="flex flex-col justify-center h-full"
              style={{ marginTop: "110px" }}
            >
              <div className="flex justify-center">
                <span
                  className="animate-flash"
                  style={{
                    fontFamily: "NexonGothic",
                    fontWeight: "bold",
                    fontSize: "40px",
                    marginBottom: "10px",
                  }}
                >
                  반갑네&nbsp;
                </span>
                <span
                  style={{
                    fontFamily: "NexonGothic",
                    fontWeight: "bold",
                    fontSize: "40px",
                    marginBottom: "10px",
                  }}
                >
                  {nickname}
                </span>
              </div>
              <div className="flex justify-center">
                <p
                  style={{
                    fontFamily: "NexonGothic",
                    fontWeight: "bold",
                    fontSize: "30px",
                    marginBottom: "20px",
                  }}
                >
                  치열한 승부 끝에 가장 높은 현상금을 차지하길 바라네
                </p>
              </div>
              <div className="flex justify-center">
                <div className="mt-7">
                  <TextButton
                    width="150px"
                    height="40px"
                    fontSize="60px"
                    text="START"
                    onClick={closeModal}
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default EntryModal;
