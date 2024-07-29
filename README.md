ThreeDee App
Target
The ThreeDee App aims to compile 2D photos into a 360-degree view that supports gyro movements for an immersive experience.
Utility
Photo Upload: Users can upload photos that include a panoramic view as well as single images of each side to enhance the stitching process.
360-Degree View: The app processes these photos to create a seamless 360-degree image that users can view and interact with.
Backend Services
Photo Upload and Stitching
Photo Upload:
Users upload multiple photos, including panoramic shots and individual side images, through the app.
The photos are sent to the server for processing.
Photo Stitching:
The server receives the uploaded photos and uses image stitching algorithms to create a panoramic view.
The stitched panoramic image is stored on the server for retrieval.
Viewing the 360-Degree Image
Image Retrieval:
When a user wants to view the 360-degree image, the app sends a GET request to the server.
The server responds with the stitched panoramic image.
Display Using Pannellum API:
The app utilizes the Pannellum API to render the 360-degree image within a WebView component.
The Pannellum API allows for smooth interactions, including gyro movements, to enhance the user experience.
Detailed Backend
1. Server Setup
Server Framework: Choose a backend framework like Node.js with Express, Python with Flask or Django, or any other suitable framework.
Storage: Use cloud storage (e.g., AWS S3, Google Cloud Storage) for storing the uploaded photos and stitched images.
Database: Use a database (e.g., MongoDB, PostgreSQL) to manage metadata related to the photos and users.
2. Photo Upload Endpoint
Endpoint: /upload
Method: POST
Request:
Headers: Content-Type: multipart/form-data
Body: Form data containing the photos.
Response:
Success: { "status": "success", "message": "Photos uploaded successfully", "image_id": "<unique_image_id>" }
Failure: { "status": "error", "message": "Upload failed" }
3. Photo Stitching Service
Image Processing Library: Use libraries like OpenCV, Panorama, or proprietary algorithms to stitch the images.
Steps:
Retrieve uploaded photos from the storage.
Apply image stitching algorithms to create a seamless panoramic view.
Save the stitched image back to the storage.
4. Image Retrieval Endpoint
Endpoint: /image/<image_id>
Method: GET
Request:
Parameters: image_id (Unique identifier for the stitched image)
Response:
Success: { "status": "success", "image_url": "<URL_of_stitched_image>" }
Failure: { "status": "error", "message": "Image not found" }
5. Pannellum API Integration
WebView Component: Integrate a WebView component in the app to display the 360-degree image.
Pannellum Configuration:
javascript
Copy code
pannellum.viewer('panorama', {
    "type": "equirectangular",
    "panorama": "<URL_of_stitched_image>",
    "autoLoad": true,
    "compass": true,
    "orientationOnByDefault": true
});


Gyro Movements: Enable gyro movements for devices that support it to provide an immersive experience.
6. Security and Scalability
Authentication: Implement user authentication to ensure secure access to the app and its features.
Rate Limiting: Apply rate limiting to prevent abuse of the upload and retrieval endpoints.
Scalability: Use load balancers and auto-scaling features to handle increased traffic and ensure high availability.
Conclusion
The ThreeDee App combines user-friendly photo upload features with advanced image processing and rendering capabilities to deliver a seamless 360-degree viewing experience. By leveraging backend services for photo stitching and the Pannellum API for displaying the 360-degree image, the app offers an engaging and interactive way for users to view their panoramic photos.


Backend Link:
https://github.com/Jangyaseni666/ThreeDee_Backend.git

Video:
https://drive.google.com/file/d/1S8l8kKt0kvflAhOOw27eiKheHtzhPNOL/view?usp=sharing
