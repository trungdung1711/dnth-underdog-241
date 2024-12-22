import imgBanner from "../../assets/img/banner.png";

function Banner() {
   return (
      <div className="container">
         <div className="row bg-dark">
            <div className="col-6 d-flex flex-column justify-content-center align-items-center">
               <h3 className="text-danger text-center p-auto mb-4">Quần luxury cho cảm giác thoải mãi</h3>
               <button className="btn btn-danger">Mua ngay</button>
            </div>
            <div className="col-6">
               <img src={imgBanner}></img>
            </div>
         </div>
      </div>
   );
}

export default Banner;