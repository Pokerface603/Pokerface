import CrpytoJS from "crypto-js";

export function hashOpenviduTitle(title) {
  const encrypted = CrpytoJS.AES.encrypt(title, "secretKey")
    .toString()
    .replace(/\+/g, "p1L2u3S")
    .replace(/\//g, "s1L2a3S4h")
    .replace(/=/g, "e1Q2u3A4l");

  return encrypted;
}
