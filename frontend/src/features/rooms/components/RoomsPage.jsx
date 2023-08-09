import React, { useEffect, useState } from "react";
import Parchment from "@component/Parchment";
import WoodBackground from "@component/WoodBackground";
import Button from "@component/Button";
import MyInfo from "@component/myinfo/MyInfo";
import StartButton from "./StartButton";
import Logo from "./Logo";
import SearchBar from "./SearchBar";
import Ranking from "./rank/Ranking";
import ConnectList from "./connect/ConnectList";
import Tab from "./Tab/Tab";
import RoomMakeModal from "./RoomMakeModal";
import { getRooms, quickStart, searchRoomsWithKeyword } from "../api/room";
import Room from "./RoomCard/RoomCard";
import { getRankers } from "../api/ranker";
import { useNavigate } from "react-router-dom";
import Navigator from "./Paging/Navigator";

const RoomsPage = () => {
  const [showRoomMakeModal, setShowRoomMakeModal] = useState(false);
  const [rooms, setRooms] = useState([]);
  const [mode, setMode] = useState("NORMAL");
  const [searchKeyword, setSearchKeyword] = useState("");
  const [rankers, setRankers] = useState([]);
  const [curPage, setCurPage] = useState(1);

  const navigate = useNavigate();

  const onClickTab = (selectedMode) => {
    setMode(selectedMode);

    if (mode === selectedMode) {
      fetchRoomData();
    }
  };

  const onClickMakeRoom = () => {
    setShowRoomMakeModal(true);
  };

  const closeModal = () => {
    setShowRoomMakeModal(false);
  };

  const onInputSearchKeyword = (e) => {
    const {
      target: { value },
    } = e;

    setSearchKeyword(value);
  };

  const onQuickStart = async () => {
    const { token, sessionId } = await quickStart();
    navigate(`game/${sessionId}`);
  };

  const getTotalPageNumber = () => {
    if (!rooms) {
      return 0;
    }

    const totalRooms = rooms.length;

    if (totalRooms % 6 === 0) {
      return parseInt(totalRooms / 6);
    }

    return parseInt(totalRooms / 6) + 1;
  };

  const calStartRoomIdx = () => {
    return 6 * (curPage - 1);
  };

  const calEndRoomIdx = () => {
    return 6 * curPage - 1;
  };

  const navigatePage = (pageNumber) => {
    setCurPage(pageNumber);
  };

  async function fetchRoomData() {
    const rooms = await getRooms(mode);
    setRooms(rooms);
  }

  async function searchRooms() {
    const rooms = await searchRoomsWithKeyword(mode, searchKeyword);
    setSearchKeyword("");
    setRooms(rooms);
  }

  async function fetchRankers() {
    const rankers = await getRankers();
    setRankers(rankers);
  }

  useEffect(() => {
    fetchRoomData();
  }, [mode]);

  useEffect(() => {
    // Todo: API 배포 후 주석 풀 예정
    // fetchRankers();
  }, []);

  return (
    <WoodBackground>
      <div className="grid grid-flow-col gap-4">
        <div className="col-span-1">
          <div className style={{ width: "1280px", height: "870px" }}>
            <div className="h-1/4">
              <div className="grid grid-cols-3 gap-10 h-full">
                <div className="col-span-2 grid mb-3">
                  <Logo />
                  <div style={{ width: "660px" }}>
                    <SearchBar
                      onClickSearch={searchRooms}
                      onInputSearchKeyword={onInputSearchKeyword}
                      searchKeyword={searchKeyword}
                    />
                  </div>
                </div>
                <div>
                  <StartButton
                    onClickCreateRoom={onClickMakeRoom}
                    onQuickStart={onQuickStart}
                  />
                </div>
              </div>
            </div>

            <Parchment
              style={{ width: "1280px", height: "652px", display: "flex" }}
            >
              <div className="flex flex-col justify-center items-center mx-auto">
                <div
                  style={{
                    width: "1225px",
                    height: "105px",
                    background: "var(--ocher)",
                  }}
                >
                  <Tab onClickTab={onClickTab} />
                </div>

                {/* 방 정보들이 들어가야하는 부분 */}
                <div
                  style={{
                    width: "1225px",
                    height: "520px",
                    background: "var(--ocher)",
                    alignContent: "start",
                    padding: "15px",
                  }}
                  className="justify-between flex flex-col"
                >
                  <div
                    className="w-full flex flex-wrap content-start"
                    style={{
                      gap: "10px",
                      flex: "1 0 auto",
                    }}
                  >
                    {rooms
                      .filter((_, idx) => {
                        return (
                          calStartRoomIdx() <= idx && idx <= calEndRoomIdx()
                        );
                      })
                      .map((room) => (
                        <Room id={room.title} {...room} />
                      ))}
                  </div>

                  <div className="flex justify-center w-full">
                    <Navigator
                      totalPage={getTotalPageNumber()}
                      onClickPage={navigatePage}
                    />
                  </div>
                </div>
              </div>
            </Parchment>
          </div>
        </div>
        <div className="col-span-1">
          <div
            style={{
              width: "510px",
              height: "870px",
            }}
          >
            <div className="h-1/5 flex justify-center items-center">
              {/* 임의의 값 입력 */}
              <MyInfo
                width="450px"
                height="160px"
                tier="A"
                nickname="닉네임"
                reward="999"
                wins="2000"
                totalMatches="4000"
                button={
                  <Button
                    width="103px"
                    height="35px"
                    label="내 정보"
                    background="white"
                  />
                }
              />
            </div>
            <div className="h-2/5 flex justify-center items-center">
              <Parchment style={{ width: "450px", height: "320px" }}>
                <div className="mt-5">
                  <Ranking rankers={rankers} />
                </div>
              </Parchment>
            </div>
            <div className="h-2/5 flex justify-center items-center">
              <Parchment style={{ width: "450px", height: "320px" }}>
                <div className="mt-1">
                  {/* 임의의 값 입력 */}
                  <ConnectList
                    tier="J"
                    nickname="Nana"
                    button={
                      <Button
                        width="130px"
                        height="30px"
                        label="같이하기"
                        background="var(--brown_dark)"
                        color="white"
                      />
                    }
                  />
                </div>
              </Parchment>
            </div>
          </div>
        </div>
      </div>
      {showRoomMakeModal && <RoomMakeModal close={closeModal} />}
    </WoodBackground>
  );
};

export default RoomsPage;
