import styles from "./css/CategorySlider.module.css";
import bannerImage from '../../assets/img/banner.png';

function CategorySlider() {
   return (
      <div className="container-fluid">
         <div className="row">
            <ul className="col-2 list-group rounded-0 p-0 d-flex flex-column text-center fs-4">
               <li className={`${styles.hoverBgRed} list-group-item cursor-pointer flex-grow-1 align-content-center`}><p>SHIRT</p></li>
               <li className={`${styles.hoverBgRed} list-group-item cursor-pointer flex-grow-1 align-content-center`}>PANTS</li>
               <li className={`${styles.hoverBgRed} list-group-item cursor-pointer flex-grow-1 align-content-center`}>SHOES & SANDAL</li>
               <li className={`${styles.hoverBgRed} list-group-item cursor-pointer flex-grow-1 align-content-center`}>JEWELRY</li>
               <li className={`${styles.hoverBgRed} list-group-item cursor-pointer flex-grow-1 align-content-center`}>BACKPACK & HANDBAG</li>
               <li className={`${styles.hoverBgRed} list-group-item cursor-pointer flex-grow-1 align-content-center`}>PERFUME</li>
            </ul>
            <div className="col-10 p-0 d-flex flex-column">
               <div id="carouselExampleDark" className="carousel carousel-dark slide flex-grow-1">
                  <div className="carousel-indicators">
                     <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
                     <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>
                     <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>
                  </div>
                  <div className="carousel-inner">
                     <div className="carousel-item active" data-bs-interval="10000">
                        <img src={bannerImage} className="d-block w-100" alt="..." />
                     </div>
                     <div className="carousel-item" data-bs-interval="2000">
                        <img src={bannerImage} className="d-block w-100" alt="..." />
                     </div>
                     <div className="carousel-item">
                        <img src={bannerImage} className="d-block w-100" alt="..." />
                     </div>
                  </div>
                  <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
                     <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                  </button>
                  <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
                     <span className="carousel-control-next-icon" aria-hidden="true"></span>
                  </button>
               </div>
            </div>
         </div>
      </div>
   );
}

export default CategorySlider;
