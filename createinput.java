  ShabavizeVolleyMultipartRequest volleyMultipartRequest = new ShabavizeVolleyMultipartRequest(Request.Method.POST, url,
                    new Response.Listener<NetworkResponse>() {
                        @Override
                        public void onResponse(NetworkResponse response) {


                            try {


                                JSONObject jsonObject = new JSONObject(new String(response.data));

                                String result = jsonObject.getString("result");

                                builder.setTitle("");
                                builder.setMessage(result);
                                displayAlert("");

                                finish();


                            } catch (JSONException e) {

                                Toast.makeText(createinput.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(createinput.this, error.getMessage(), Toast.LENGTH_SHORT).show();


                }
            }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();

                    params.put("code", s_code);
                    params.put("group", s_group);
                    params.put("title", s_title);
                    params.put("date", s_date);
                    params.put("locate", s_locate);
                    params.put("des", s_des);
                    params.put("user", s_user);

                    return params;
                }

                @Override
                protected Map<String, DataPart> getByteData() throws AuthFailureError {

                    Map<String, DataPart> params = new HashMap<>();
                    long imagename = System.currentTimeMillis();
                    params.put("pic", new DataPart(imagename + ".jpg", getfiledatafromdrawable(bitmap)));


                    return params;
                }
            };


            Volley.newRequestQueue(this).add(volleyMultipartRequest);
