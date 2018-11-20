f(shape instanceof Circle) {
				double newRadius  = this.radius + ((Circle)shape).radius;
							return (shape.position()[0] <= this.position()[0] + newRadius
														||  shape.position()[0] <= this.position()[0] - newRadius)
												&& (shape.position()[1] <= this.position()[1] + newRadius
																				||  shape.position()[1] <= this.position()[1] - newRadius);
									}
