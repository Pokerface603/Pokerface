import Input from "@component/Input";
import CheckIcon from "./CheckIcon";

import {
  height,
  nameWidth,
  pwWidth,
  checkWidth,
  idStyles,
  nicknameStyles,
  passwordStyles,
  passCheckStyles,
} from "./registFormStyle";

const RegistForm = () => {
  return (
    <div
      className="grid grid-cols-1 justify-items-center content-center"
      style={{ width: "912px", height: "333px" }}>
      <div className="flex">
        <Input
          width={nameWidth}
          height={height}
          type={idStyles.type}
          placeholder={idStyles.placeholder}
        />
        <CheckIcon width={checkWidth} height={height} />
      </div>
      <div className="flex">
        <Input
          width={nameWidth}
          height={height}
          type={nicknameStyles.type}
          placeholder={nicknameStyles.placeholder}
        />
        <CheckIcon width={checkWidth} height={height} />
      </div>
      <Input
        width={pwWidth}
        height={height}
        type={passwordStyles.type}
        placeholder={passwordStyles.placeholder}
      />
      <Input
        width={pwWidth}
        height={height}
        type={passCheckStyles.type}
        placeholder={passCheckStyles.placeholder}
      />
    </div>
  );
};

export default RegistForm;
