function Category() {
   return (
      <div class="container">
         <div className="row mt-4">
            <div className="col-1 bg-danger rounded-1 ps-1 ms-lg-2" style={{ width: "1em", height: "2em" }}></div>
            <div className="col-11 text-danger align-content-center"><h4>CATEGORY</h4></div>
         </div>
         <div class="row justify-content-center mt-4 mb-4">
            <div class="col-1 btn btn-danger me-4 p-md-4">
               <i className="fas fa-shirt"></i>
               <p class="card-text">SHIRT</p>
            </div>
            <div class="col-1 btn btn-danger me-4 p-md-4">
               <i className="fas fa-shirt"></i>
               <p class="card-text">PANTS</p>
            </div>
            <div class="col-1 btn btn-danger me-4 pt-md-4">
               <i className="fas fa-shirt"></i>
               <p class="card-text">SHOES & SANDAL</p>
            </div>
            <div class="col-1 btn btn-danger me-4 p-md-4">
               <i className="fas fa-shirt"></i>
               <p class="card-text">BACKPACK & HANDBAG</p>
            </div>
            <div class="col-1 btn btn-danger me-4 pt-md-4 align-content-center">
               <i className="fas fa-shirt"></i>
               <p class="card-text">JEWELRY</p>
            </div>
            <div class="col-1 btn btn-danger me-4 p-md-4">
               <i className="fas fa-shirt"></i>
               <p class="card-text">PERFUME</p>
            </div>            
         </div>
      </div>
   );
}

export default Category;